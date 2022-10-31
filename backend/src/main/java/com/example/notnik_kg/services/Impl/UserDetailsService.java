package com.example.notnik_kg.services.Impl;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.repositories.UserRepo;
import com.example.notnik_kg.security.UserEntityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepo.findUserEntityByEmail(email);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new UserEntityDetails(user.get());
    }
}
