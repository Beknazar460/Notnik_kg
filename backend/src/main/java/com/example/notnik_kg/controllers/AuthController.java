package com.example.notnik_kg.controllers;

import com.example.notnik_kg.models.LoginRequest;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.services.Impl.RegistrationService;
import com.example.notnik_kg.util.UserConfirmPasswordValidator;
import com.example.notnik_kg.util.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Контроллер для создания пользователей",
        description = "В этом контроллере вы сможете регистрировать новых пользователей"
)
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

    @PostMapping("/signin")
    @Operation(
            summary = "Отправить логин и пароль для аутентификации",
            description = "Позволяет аутентифицироваться"
    )
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/registration")
    @Operation(
            summary = "Создание нового пользователя",
            description = "Позволяет создавать нового пользователя"
    )
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