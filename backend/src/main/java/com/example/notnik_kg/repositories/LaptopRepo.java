package com.example.notnik_kg.repositories;

import com.example.notnik_kg.entities.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepo extends JpaRepository<LaptopEntity, Long> {

    LaptopEntity findByTitle(String title);

}
