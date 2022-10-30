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
    private Long userId;
    private Long laptopId;

    public static OrderModel orderModel(OrderEntity orderEntity) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(orderEntity.getId());
        orderModel.setTitleOfProduct(orderEntity.getTitleOfProduct());
        orderModel.setPriceOfProduct(orderEntity.getPriceOfProduct());
        orderModel.setUserId(orderEntity.getUser().getId());
        orderModel.setLaptopId(orderEntity.getLaptop().getId());
        return orderModel;
    }

}