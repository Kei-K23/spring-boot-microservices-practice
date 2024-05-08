package dev.kei.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.kei.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
