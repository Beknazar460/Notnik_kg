package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserModel;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.repositories.UserRepo;
import com.example.notnik_kg.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        try {
            LocalDateTime dateOfRegistration = LocalDateTime.now();
            UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
            userRepo.save(userEntity);
            return new ResponseEntity<String>("User is created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("User isn't created", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getUserId(Long id) {
        try {
            UserEntity user = userRepo.findById(id).get();
            return ResponseEntity.ok(UserModel.toUser(user));
        } catch (Exception e) {
            return new ResponseEntity<>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
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
