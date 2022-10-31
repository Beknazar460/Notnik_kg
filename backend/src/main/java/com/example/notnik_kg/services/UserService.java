package com.example.notnik_kg.services;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();
    ResponseEntity<?> createUser(UserRequest userRequest);
    ResponseEntity<?> getUserId(Long id);
    ResponseEntity<String> deleteUser(Long id);
    ResponseEntity<?> updateUser(Long id, UserRequest userRequest);
}
