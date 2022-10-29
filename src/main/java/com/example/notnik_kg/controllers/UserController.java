package com.example.notnik_kg.controllers;

import com.example.notnik_kg.services.Impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;
    @Autowired
    public UserController(UserServiceImpl userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
}
