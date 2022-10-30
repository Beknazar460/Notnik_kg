package com.example.notnik_kg.services;

import com.example.notnik_kg.entities.OrderEntity;
import com.example.notnik_kg.models.OrderModel;
import com.example.notnik_kg.models.OrderRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> getAllOrders();
    ResponseEntity<?> createOrder(OrderRequest orderRequest);
    ResponseEntity<String> deleteOrder(Long id);
}
