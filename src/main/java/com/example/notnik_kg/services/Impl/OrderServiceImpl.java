package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.OrderEntity;
import com.example.notnik_kg.models.OrderModel;
import com.example.notnik_kg.repositories.LaptopRepo;
import com.example.notnik_kg.repositories.OrderRepo;
import com.example.notnik_kg.repositories.UserRepo;
import com.example.notnik_kg.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final LaptopRepo lapTopRepo;
    private final UserRepo userRepo;
    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, LaptopRepo lapTopRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.lapTopRepo = lapTopRepo;
        this.userRepo = userRepo;
    }


    @Override
    public ResponseEntity<?> createOrder(OrderModel orderModel) {
        if (userRepo.findById(orderModel.getUserId()).isPresent() && lapTopRepo.findById(orderModel.getLaptopId()).isPresent()) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setTitleOfProduct(orderModel.getTitleOfProduct());
            orderEntity.setPriceOfProduct(orderModel.getPriceOfProduct());
            orderEntity.setUser(userRepo.findById(orderModel.getUserId()).get());
            orderEntity.setLapTop(lapTopRepo.findById(orderModel.getLaptopId()).get());
            orderRepo.save(orderEntity);
            return ResponseEntity.ok("Order is created");
        }
        return new ResponseEntity<String>("Such a user or product does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteOrder(Long id) {
        if (orderRepo.existsById(id)) {
            orderRepo.deleteById(id);
            return ResponseEntity.ok("Order is deleted");
        }
        else return new ResponseEntity<String>("There is no such order", HttpStatus.NOT_FOUND);
    }
}
