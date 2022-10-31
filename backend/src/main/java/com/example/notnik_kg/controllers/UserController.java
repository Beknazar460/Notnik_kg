package com.example.notnik_kg.controllers;

import com.example.notnik_kg.models.UserModel;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.services.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(
        name = "Контроллер для получения пользователей",
        description = "В этом контроллере вы можете получать пользователей"
)
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    @Operation(
            summary = "Получение пользователей",
            description = "Позволяет получать всех пользователей"
    )
    public List<UserModel> getUsers(){
        return userService.getListAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение пользователей по ID",
            description = "Позволяет полуать пользователя по ID"
    )
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        return userService.getUserId(id);
    }
}
