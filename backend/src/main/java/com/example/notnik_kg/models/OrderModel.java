package com.example.notnik_kg.models;

import com.example.notnik_kg.entities.OrderEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderModel {

    private String laptopTitle;
    private LocalDateTime orderDate;
    private int priceOfProduct;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public static OrderModel orderModel(OrderEntity orderEntity) {
        OrderModel orderModel = new OrderModel();
        orderModel.setLaptopTitle(orderEntity.getLaptop().getTitle());
        orderModel.setOrderDate(orderEntity.getOrderDate());
        orderModel.setPriceOfProduct(orderEntity.getPriceOfProduct());
        orderModel.setFirstName(orderEntity.getUser().getFirstName());
        orderModel.setLastName(orderEntity.getUser().getLastName());
        orderModel.setPhoneNumber(orderEntity.getUser().getPhoneNumber());
        return orderModel;
    }

    public static List<OrderModel> listOrderModel(List<OrderEntity> orderEntities) {
        List<OrderModel> orderModelList = new ArrayList<>();
        for (OrderEntity order:orderEntities
             ) {
            orderModelList.add(OrderModel.orderModel(order));
        }
        return orderModelList;
    }

}