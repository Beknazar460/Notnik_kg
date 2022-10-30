package com.example.notnik_kg.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty(message = "Email should not be empty")
    @Size(min = 1, max = 50, message = "email must be between 1 and 50 characters")
    @Email(message = "Email is not validated")
    private String email;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 1, max = 50, message = "first name must be between 1 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 1, max = 50, message = "last name must be between 1 and 50 characters")
    private String lastName;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 1, max = 50, message = "password must be between 1 and 50 characters")
    private String password;

    private String confirmPass;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 1, max = 50, message = "phone number must be between 1 and 50 characters")
    private String phoneNumber;

}
