package com.example.notnik_kg.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotEmpty(message = "Title can not be empty")
    private String titleOfProduct;

    @NotEmpty(message = "User id can't be empty")
    private Long userId;

}
