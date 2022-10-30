package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.LaptopEntity;
import com.example.notnik_kg.entities.OrderEntity;
import com.example.notnik_kg.models.OrderRequest;
import com.example.notnik_kg.repositories.LaptopRepo;
import com.example.notnik_kg.repositories.OrderRepo;
import com.example.notnik_kg.repositories.UserRepo;
import com.example.notnik_kg.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<OrderEntity> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public ResponseEntity<?> createOrder(OrderRequest orderRequest) {
        try {
            if (userRepo.findById(orderRequest.getUserId()).isPresent() && lapTopRepo.findById(orderRequest.getLaptopId()).isPresent()) {
                OrderEntity orderEntity = new OrderEntity();
                LaptopEntity laptopEntity = lapTopRepo.findByTitle(orderRequest.getTitleOfProduct());
                orderEntity.setTitleOfProduct(laptopEntity.getTitle());
                orderEntity.setPriceOfProduct(laptopEntity.getPrice());
                orderEntity.setUser(userRepo.findById(orderRequest.getUserId()).get());
                orderEntity.setLapTop(lapTopRepo.findById(orderRequest.getLaptopId()).get());
                orderRepo.save(orderEntity);
                return ResponseEntity.ok("Order is created");
            }
            else {
                return new ResponseEntity<String>("Such a user or product does not exist", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error create new order", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteOrder(Long id) {
        try {
            if (orderRepo.existsById(id)) {
                orderRepo.deleteById(id);
                return ResponseEntity.ok("Order is deleted");
            }
            else return new ResponseEntity<String>("There is no such order", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error delete order", HttpStatus.BAD_REQUEST);
        }
    }
}
