package com.example.notnik_kg.models;

import com.example.notnik_kg.entities.OrderEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderModel {

    private String laptopTitle;
    private int priceOfProduct;
    private String firstName;
    private String lastName;

    public static OrderModel orderModel(OrderEntity orderEntity) {
        OrderModel orderModel = new OrderModel();
        orderModel.setLaptopTitle(orderEntity.getLaptop().getTitle());
        orderModel.setPriceOfProduct(orderEntity.getPriceOfProduct());
        orderModel.setFirstName(orderEntity.getUser().getFirstName());
        orderModel.setLastName(orderEntity.getUser().getLastName());
        return orderModel;
    }

}