package com.example.notnik_kg.controllers;

import com.example.notnik_kg.models.OrderModel;
import com.example.notnik_kg.services.Impl.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;


    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    private ResponseEntity<?> createOrder(@RequestBody OrderModel orderModel) {
        return orderService.createOrder(orderModel);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
}
