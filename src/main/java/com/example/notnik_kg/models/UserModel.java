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
import java.util.stream.Collectors;

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
    private List<OrderModel> orderEntities;

    public static UserModel userModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setEmail(userEntity.getEmail());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        userModel.setPhoneNumber(userEntity.getPhoneNumber());
        userModel.setDateOfRegistration(userEntity.getDateOfRegistration());
        userModel.setOrderEntities(userEntity.getOrderEntities().stream().map(OrderModel::orderModel).collect(Collectors.toList()));
        return userModel;
    }
}
