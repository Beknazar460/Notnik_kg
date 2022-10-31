package com.example.notnik_kg.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность заказа")
public class OrderRequest {


    @Schema(description = "Название товара")
    @NotEmpty(message = "Title can not be empty")
    private String titleOfProduct;

    @Schema(description = "Идентификатор пользователя")
    @NotEmpty(message = "User id can't be empty")
    private Long userId;

}
