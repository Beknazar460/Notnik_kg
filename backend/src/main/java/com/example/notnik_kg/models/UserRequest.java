package com.example.notnik_kg.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    @Pattern(regexp = "([a-z]+[A-Z]+[0-9]+|[a-z]+[0-9]+[A-Z]+|[A-Z]+[a-z]+[0-9]+|[A-Z]+[0-9]+[a-z]+|[0-9]+[a-z]+[A-Z]+|[0-9]+[A-Z]+[a-z]+)", message = "6 to 20 characters. The password must contain at least one number, one lowercase letter, and one uppercase letter.")
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 1, max = 100, message = "Password must be between 1 and 100 characters")
    private String password;

    @NotEmpty(message = "Confirm password should not be empty")
    @Size(min = 1, max = 50, message = "password must be between 1 and 50 characters")
    private String confirmPass;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 1, max = 50, message = "phone number must be between 1 and 50 characters")
    private String phoneNumber;

}
