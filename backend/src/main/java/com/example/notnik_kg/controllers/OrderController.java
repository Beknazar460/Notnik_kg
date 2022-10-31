package com.example.notnik_kg.controllers;

import com.example.notnik_kg.models.OrderModel;
import com.example.notnik_kg.models.OrderRequest;
import com.example.notnik_kg.services.Impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag (
        name = "Контроллер для управления с заказами",
        description = "В этом контроллере вы сможете создавать и удалять заказы"
)
public class OrderController {

    private final OrderServiceImpl orderService;


    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(
            summary = "Получение заказов",
            description = "Позволяет получить все заказы"
    )
    private List<OrderModel> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    @Operation(
            summary = "Создание заказа",
            description = "Позволяет создать заказ введя его данные"
    )
    private ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление заказа",
            description = "Позволяет удалять заказы по идентификатору"
    )
    private ResponseEntity<?> deleteOrder(@PathVariable
                                              @Parameter(description = "Идентификатор заказа")
                                                Long id) {
        return orderService.deleteOrder(id);
    }
}
