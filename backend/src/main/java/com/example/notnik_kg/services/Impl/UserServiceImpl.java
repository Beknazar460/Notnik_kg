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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<UserModel> getListAllUsers() {
        return UserModel.listLUserModel(userRepo.findAll());
    }

    @Transactional
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        try {
            UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
            userEntity.setDateOfRegistration(LocalDateTime.now());
            userEntity.setRole("ROLE_USER");
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

            userRepo.save(userEntity);
            return new ResponseEntity<String>("User was created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("User wasn't created", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<?> getUserId(Long id) {
        try {
            UserEntity user = userRepo.findById(id).get();
            return ResponseEntity.ok(UserModel.userModel(user));
        } catch (Exception e) {
            return new ResponseEntity<>("User with ID " + id + " wasn't found", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<String> deleteUser(Long id) {
        try {
            userRepo.deleteById(id);
            return new ResponseEntity<String>("User was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("User wasn't found", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<?> updateUser(Long id, UserRequest userRequest) {
        if(userRepo.findById(id).isEmpty()){
            return new ResponseEntity<>("User with ID " + id + " wasn't found", HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        userEntity.setId(id);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setDateOfRegistration(LocalDateTime.now());
        userEntity.setRole("ROLE_USER");
        userRepo.save(userEntity);

        return ResponseEntity.ok("User with ID " + id + " was updated");
    }
}
