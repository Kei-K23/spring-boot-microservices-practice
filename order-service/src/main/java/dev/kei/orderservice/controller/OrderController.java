package dev.kei.orderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.kei.orderservice.dto.OrderRequest;
import dev.kei.orderservice.dto.OrderResponse;
import dev.kei.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory", fallbackMethod = "fallbackMethod")
    public CompletableFuture<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> {
            orderService.placeOrder(orderRequest);
            return  OrderResponse.builder().message("Order placed successfully.").code(201).build();
        });
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public  CompletableFuture<OrderResponse> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        log.info("Calling fallback method for save order post method");
        return CompletableFuture.supplyAsync(() -> OrderResponse.builder().message("Oops! Something went wrong. Please try again later.").code(500).build());
    }
}
