package com.example.notnik_kg.models;

import com.example.notnik_kg.entities.LaptopEntity;
import com.example.notnik_kg.entities.OrderEntity;
import com.example.notnik_kg.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaptopModel {
    private static ModelMapper modelMapper = new ModelMapper();

    private Long id;
    private String title;
    private int price;
    private String description;
    private List<OrderModel> orderEntities;

    public static LaptopModel laptopModel(LaptopEntity laptopEntity) {
        LaptopModel laptopModel = new LaptopModel();
        laptopModel.setId(laptopEntity.getId());
        laptopModel.setTitle(laptopEntity.getTitle());
        laptopModel.setPrice(laptopEntity.getPrice());
        laptopModel.setOrderEntities(laptopEntity.getOrderEntities().stream().map(OrderModel::orderModel).collect(Collectors.toList()));
        return laptopModel;
    }

    public static List<LaptopModel> listLaptopModel(List<LaptopEntity> laptopEntities) {
        List<LaptopModel> listLaptopModel = new ArrayList<>();
        for (LaptopEntity laptop:laptopEntities
        ) {
            listLaptopModel.add(LaptopModel.laptopModel(laptop));
        }
        return listLaptopModel;
    }
}
