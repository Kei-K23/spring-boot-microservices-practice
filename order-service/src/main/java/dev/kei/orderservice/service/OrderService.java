package dev.kei.orderservice.service;

import org.springframework.stereotype.Service;

import dev.kei.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

        
}
