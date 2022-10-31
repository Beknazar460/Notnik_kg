package com.example.notnik_kg.controllers;

import com.example.notnik_kg.entities.LaptopEntity;
import com.example.notnik_kg.models.LaptopModel;
import com.example.notnik_kg.models.LaptopRequest;
import com.example.notnik_kg.services.Impl.LaptopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/laptops")
public class LaptopController {
    private final LaptopServiceImpl laptopService;

    @Autowired
    public LaptopController(LaptopServiceImpl laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping()
    public List<LaptopModel> getLaptops(){
        return laptopService.getListLaptops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLaptopById(@PathVariable("id") Long id){
        return laptopService.getLaptopById(id);
    }

    @PostMapping()
    public ResponseEntity<String> addLaptop(@RequestBody @Valid LaptopRequest laptopRequest){
        return laptopService.addLaptop(laptopRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLaptop(@PathVariable("id") Long id,
                                          @RequestBody @Valid LaptopRequest laptopRequest){
        return laptopService.updateLaptop(id, laptopRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLaptop(@PathVariable("id") Long id){
        return laptopService.deleteLaptop(id);
    }
}
