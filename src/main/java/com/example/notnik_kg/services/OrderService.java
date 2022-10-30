package com.example.notnik_kg.services;

import com.example.notnik_kg.models.OrderModel;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> createOrder(OrderModel orderModel);
    ResponseEntity<String> deleteOrder(Long id);
}
