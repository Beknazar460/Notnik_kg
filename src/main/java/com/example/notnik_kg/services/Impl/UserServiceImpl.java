package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.services.UserService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<UserEntity> getAllUsers() {
        return null;
    }

    @Override
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getUserId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserRequest userRequest) {
        return null;
    }
}
