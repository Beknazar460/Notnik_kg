package com.example.notnik_kg.controllers;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.services.Impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserEntity> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        return userService.getUserId(id);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest userRequest){
        return userService.createUser(userRequest);
    }

}
