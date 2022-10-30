package com.example.notnik_kg.services;

import com.example.notnik_kg.entities.LaptopEntity;
import com.example.notnik_kg.models.LaptopModel;
import com.example.notnik_kg.models.LaptopRequest;
import com.example.notnik_kg.models.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LaptopService {
    List<LaptopEntity> getLaptops();
    ResponseEntity<?> getLaptopById(Long id);
    ResponseEntity<?> addLaptop(LaptopRequest laptopRequest);
    ResponseEntity<String> deleteLaptop(Long id);
    ResponseEntity<?> updateLaptop(Long id, LaptopRequest laptopRequest);
}
