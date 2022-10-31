package com.example.notnik_kg.controllers;

import com.example.notnik_kg.models.LoginRequest;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.services.Impl.RegistrationService;
import com.example.notnik_kg.util.UserConfirmPasswordValidator;
import com.example.notnik_kg.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    private final UserConfirmPasswordValidator userConfirmPasswordValidator;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator, UserConfirmPasswordValidator userConfirmPasswordValidator, AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.userConfirmPasswordValidator = userConfirmPasswordValidator;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/registration")
    public String performRegistration(@RequestBody @Valid UserRequest userRequest,
                                      BindingResult bindingResult) {
        userValidator.validate(userRequest, bindingResult);
        userConfirmPasswordValidator.validate(userRequest, bindingResult);

        if (bindingResult.hasErrors())
            return bindingResult.getFieldErrors().get(0).getDefaultMessage();

        registrationService.register(userRequest);

        return "Registration successful";
    }
}