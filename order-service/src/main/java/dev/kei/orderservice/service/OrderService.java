package dev.kei.orderservice.service;

import dev.kei.orderservice.dto.InventoryResponse;
import dev.kei.orderservice.dto.OrderItemsListDto;
import dev.kei.orderservice.dto.OrderPlacedEvent;
import dev.kei.orderservice.dto.OrderRequest;
import dev.kei.orderservice.model.Order;
import dev.kei.orderservice.model.OrderItems;
import dev.kei.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsListsDto().stream().map(this::mapToOrderItems).toList();

        order.setOrderItemsList(orderItems);

        List<String> skuCodes = order.getOrderItemsList().stream().map(OrderItems::getSkuCode).toList();

        // check order items is in stock in inventory service
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get().uri("http://inventory-service/api/v1/inventory",
                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()).retrieve().bodyToMono(InventoryResponse[].class).block();
        if (inventoryResponses != null && inventoryResponses.length > 0) {
            Boolean isInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
            if (isInStock) {
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            } else {
                throw new IllegalStateException("Product is not in stock.");
            }
        } else {
            throw new IllegalStateException("Inventory service is response empty.");
        }
    }

    private OrderItems mapToOrderItems(OrderItemsListDto orderItemsList) {
        return OrderItems.builder().skuCode(orderItemsList.getSkuCode()).price(orderItemsList.getPrice()).quantity(orderItemsList.getQuantity()).build();
    }
}
