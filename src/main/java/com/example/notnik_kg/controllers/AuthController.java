package com.example.notnik_kg.controllers;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.services.Impl.RegistrationService;
import com.example.notnik_kg.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") UserEntity person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@RequestBody @Valid UserRequest userRequest,
                                      BindingResult bindingResult) {
        userValidator.validate(userRequest, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(userRequest);

        return "redirect:/auth/login";
    }
}