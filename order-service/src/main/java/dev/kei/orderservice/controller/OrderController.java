package dev.kei.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.kei.orderservice.dto.OrderRequest;
import dev.kei.orderservice.dto.OrderSuccessResponse;
import dev.kei.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderSuccessResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return OrderSuccessResponse.builder().message("Order placed successfully.").code(201).build();
    }
}
