package com.example.notnik_kg.models;

import com.example.notnik_kg.entities.LaptopEntity;
import com.example.notnik_kg.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaptopModel {
    private static ModelMapper modelMapper = new ModelMapper();

    private Long id;
    private String title;
    private String price;
    private String description;
    private List<OrderEntity> orderEntities;

    public static LaptopModel toLaptop(LaptopEntity laptop){
        return modelMapper.map(laptop, LaptopModel.class);
    }
}
