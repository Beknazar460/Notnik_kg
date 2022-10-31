package com.example.notnik_kg.util;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserRequest;
import com.example.notnik_kg.services.Impl.UserValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserValidateService userValidateService;

    @Autowired
    public UserValidator(UserValidateService userValidateService) {
        this.userValidateService = userValidateService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequest person = (UserRequest) target;

        try {
            userValidateService.getPerson(person).orElseThrow(Exception::new);
        } catch (Exception e) {
            return;
        }
        errors.rejectValue("email", "", "Person with that email already exists");
    }
}
