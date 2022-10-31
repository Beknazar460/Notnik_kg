package com.example.notnik_kg.models;

import com.example.notnik_kg.entities.OrderEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderModel {
    private Long id;
    private String titleOfProduct;
    private int priceOfProduct;
    private String firstName;
    private String lastName;
    private String laptopTitle;

    public static OrderModel orderModel(OrderEntity orderEntity) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(orderEntity.getId());
        orderModel.setTitleOfProduct(orderEntity.getTitleOfProduct());
        orderModel.setPriceOfProduct(orderEntity.getPriceOfProduct());
        orderModel.setFirstName(orderEntity.getUser().getFirstName());
        orderModel.setLastName(orderEntity.getUser().getLastName());
        orderModel.setLaptopTitle(orderEntity.getLaptop().getTitle());
        return orderModel;
    }

}