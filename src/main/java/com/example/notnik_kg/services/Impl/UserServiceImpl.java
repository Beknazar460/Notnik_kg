package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserModel;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.repositories.UserRepo;
import com.example.notnik_kg.services.UserService;
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

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        try {
            UserEntity userEntity = new UserEntity();
            LocalDateTime dateOfRegistration = LocalDateTime.now();
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userEntity.setFirstName(userRequest.getFirstName());
            userEntity.setLastName(userRequest.getLastName());
            userEntity.setDateOfRegistration(dateOfRegistration);
            userEntity.setPhoneNumber(userRequest.getPhoneNumber());
            userEntity.setRole("USER");
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
        try {
            userRepo.deleteById(id);
            return new ResponseEntity<String>("User is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("User isn't found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserRequest userRequest) {
        return userRepo.findById(id)
                .map(userEntity -> {
            LocalDateTime dateAfterUpdate = LocalDateTime.now();
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setPassword(userRequest.getPassword());
            userEntity.setDateOfRegistration(dateAfterUpdate);
            userEntity.setFirstName(userRequest.getFirstName());
            userEntity.setLastName(userRequest.getLastName());
            userEntity.setRole("USER");
            userEntity.setPhoneNumber(userRequest.getPhoneNumber());
            userRepo.save(userEntity);
            return ResponseEntity.ok("A user with such an ID " + id + " updated");
        }).orElse(new ResponseEntity<String>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND));
    }
}
