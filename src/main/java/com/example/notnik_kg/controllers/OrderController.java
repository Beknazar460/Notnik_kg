package com.example.notnik_kg.controllers;

import com.example.notnik_kg.entities.OrderEntity;
import com.example.notnik_kg.models.OrderRequest;
import com.example.notnik_kg.services.Impl.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;


    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    private List<OrderEntity> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    private ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
}
