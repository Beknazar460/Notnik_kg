package com.example.notnik_kg.models;

import com.example.notnik_kg.entities.LaptopEntity;
import com.example.notnik_kg.entities.OrderEntity;
import com.example.notnik_kg.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private static ModelMapper modelMapper = new ModelMapper();
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime dateOfRegistration;
    private List<OrderEntity> orderEntities;

    public static UserModel toUserEntity(UserEntity userEntity){
        return modelMapper.map(userEntity, UserModel.class);
    }
}
