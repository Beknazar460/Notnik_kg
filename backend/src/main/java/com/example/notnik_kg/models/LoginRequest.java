package com.example.notnik_kg.models;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotEmpty(message = "Email should not be empty")
    @Size(min = 1, max = 50, message = "email must be between 1 and 50 characters")
    @Email(message = "Email is not validated")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 1, max = 100, message = "Password must be between 1 and 100 characters")
    private String password;

}
