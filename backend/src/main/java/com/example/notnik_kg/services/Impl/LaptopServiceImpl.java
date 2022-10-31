package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.LaptopEntity;
import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.LaptopModel;
import com.example.notnik_kg.models.LaptopRequest;
import com.example.notnik_kg.models.UserModel;
import com.example.notnik_kg.repositories.LaptopRepo;
import com.example.notnik_kg.services.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepo laptopRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public LaptopServiceImpl(LaptopRepo laptopRepo, ModelMapper modelMapper) {
        this.laptopRepo = laptopRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LaptopEntity> getLaptops() {
        return laptopRepo.findAll();
    }

    @Override
    public ResponseEntity<?> getLaptopById(Long id) {
        if(laptopRepo.findById(id).isEmpty())
            return new ResponseEntity<>("Laptop with ID " + id + " wasn't found", HttpStatus.NOT_FOUND);

        LaptopEntity laptop = laptopRepo.findById(id).get();
        return ResponseEntity.ok(LaptopModel.toLaptop(laptop));
    }

    @Override
    public ResponseEntity<String> addLaptop(LaptopRequest laptopRequest) {
        try {
            LaptopEntity laptop = modelMapper.map(laptopRequest, LaptopEntity.class);
            laptopRepo.save(laptop);

            return new ResponseEntity<>("Laptop was added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Laptop wasn't added", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteLaptop(Long id) {
        try {
            laptopRepo.deleteById(id);
            return new ResponseEntity<>("Laptop was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Laptop wasn't found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateLaptop(Long id, LaptopRequest laptopRequest) {
        if(laptopRepo.findById(id).isEmpty())
            return new ResponseEntity<>("Laptop with ID " + id + " wasn't found", HttpStatus.NOT_FOUND);

        LaptopEntity laptop = modelMapper.map(laptopRequest, LaptopEntity.class);
        laptop.setId(id);
        laptopRepo.save(laptop);

        return ResponseEntity.ok("Laptop with ID " + id + " was updated");
    }

}
