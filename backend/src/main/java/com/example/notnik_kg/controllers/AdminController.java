package com.example.notnik_kg.controllers;

import com.example.notnik_kg.models.UserModel;
import com.example.notnik_kg.services.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(
        name = "Контроллер для управления сайтом от имени администратора",
        description = "В этом контроллере вы сможете изменять и добавлять информацию на сайте"
)
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/set_admin/{id}")
    @Operation(
            summary = "Изменить роль пользователя - администратор",
            description = "Позволяет дать права администратора выбранному пользователю"
    )
    public ResponseEntity<String> addAdmin(@PathVariable("id") Long id){
        return userService.makeAdmin(id);
    }

    @PostMapping("/set_user/{id}")
    @Operation(
            summary = "Изменить роль пользователя - пользователь",
            description = "Позволяет дать права пользователя выбранному пользователю"
    )
    public ResponseEntity<String> addUser(@PathVariable("id") Long id){
        return userService.makeUser(id);
    }
}
