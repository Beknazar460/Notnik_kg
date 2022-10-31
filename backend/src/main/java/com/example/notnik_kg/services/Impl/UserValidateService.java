package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidateService {
    private final UserRepo userRepo;

    @Autowired
    public UserValidateService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public Optional<UserEntity> getPerson(UserRequest person){
        String user = person.getEmail();
        Optional<UserEntity> optionalPerson = userRepo.findUserEntityByEmail(user);
        return optionalPerson;
    }
}
