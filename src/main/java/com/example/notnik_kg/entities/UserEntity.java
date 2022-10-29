package com.example.notnik_kg.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Email should not be empty")
    @Size(min = 1, max = 50, message = "email must be between 1 and 50 characters")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 1, max = 50, message = "password must be between 1 and 50 characters")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 1, max = 50, message = "first name must be between 1 and 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 1, max = 50, message = "last name must be between 1 and 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 1, max = 50, message = "phone number must be between 1 and 50 characters")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_registration")
    private LocalDateTime dateOfRegistration;
}
