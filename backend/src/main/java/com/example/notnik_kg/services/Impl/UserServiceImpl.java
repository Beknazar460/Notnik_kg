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

    @Transactional
    public ResponseEntity<String> makeAdmin(Long id) {
        UserEntity user = userRepo.findById(id).orElse(null);
        if(user != null) {
            if(user.getRole().equals("ROLE_ADMIN")){
                return new ResponseEntity<>("Already an administrator!", HttpStatus.BAD_REQUEST);
            }
            user.setRole("ROLE_ADMIN");
            return new ResponseEntity<>("User set as administrator!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User with ID: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<String> makeUser(Long id) {
        UserEntity user = userRepo.findById(id).orElse(null);
        if(user != null) {
            if(user.getRole().equals("ROLE_USER")){
                return new ResponseEntity<>("Already an user!", HttpStatus.BAD_REQUEST);
            }
            user.setRole("ROLE_USER");
            return new ResponseEntity<>("Administrator set as user!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User with ID: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<String> userLock(Long id) {
        UserEntity user = userRepo.findById(id).orElse(null);
        if(user != null) {
            if(user.getLock() == 1){
                return new ResponseEntity<>("The user is already blocked!", HttpStatus.BAD_REQUEST);
            }
            user.setLock(1);
            return new ResponseEntity<>("User has been blocked!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User with ID: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<String> userUnlock(Long id) {
        UserEntity user = userRepo.findById(id).orElse(null);
        if(user != null) {
            if(user.getLock() == 0){
                return new ResponseEntity<>("The user is already unblocked!", HttpStatus.BAD_REQUEST);
            }
            user.setLock(0);
            return new ResponseEntity<>("User has been unblocked!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User with ID: " + id + " not found!", HttpStatus.NOT_FOUND);
    }
}
