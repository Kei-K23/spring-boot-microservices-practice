package dev.kei.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.kei.orderservice.dto.OrderItemsListDto;
import dev.kei.orderservice.dto.OrderRequest;
import dev.kei.orderservice.model.Order;
import dev.kei.orderservice.model.OrderItems;
import dev.kei.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsListsDto().stream().map(this::mapToOrderItems).toList();

        order.setOrderItemsList(orderItems);
        orderRepository.save(order);
    }

    private OrderItems mapToOrderItems(OrderItemsListDto orderItemsList) {
        return OrderItems.builder().skuCode(
                orderItemsList.getSkuCode()).price(orderItemsList.getPrice()).quantity(orderItemsList.getQuantity())
                .build();
    }
}
