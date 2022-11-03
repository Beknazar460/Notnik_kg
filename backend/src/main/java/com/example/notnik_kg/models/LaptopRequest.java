package com.example.notnik_kg.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaptopRequest {

    @NotEmpty(message = "Title can not be empty")
    private String title;

    @Min(value = 0, message = "Price should be higher than 0")
    private String price;

    @NotEmpty(message = "Description can not be empty")
    private String description;
}
