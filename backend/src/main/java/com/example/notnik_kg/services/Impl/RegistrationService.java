package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<HttpStatus> register(UserRequest person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        UserEntity user = new UserEntity();
        user.setFirstName(person.getFirstName());
        user.setLastName(person.getLastName());
        user.setEmail(person.getEmail());
        user.setPassword(person.getPassword());
        user.setPhoneNumber(person.getPhoneNumber());
        user.setRole("ROLE_USER");
        user.setLock(0);
        user.setDateOfRegistration(LocalDateTime.now());
        userRepo.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
