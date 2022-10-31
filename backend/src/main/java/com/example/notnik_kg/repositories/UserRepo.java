package com.example.notnik_kg.repositories;

import com.example.notnik_kg.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByEmail(String email);
    UserEntity findByEmail(String email);
    UserEntity findByFirstName(String firstName);
}
