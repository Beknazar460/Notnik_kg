package com.example.notnik_kg.util;

import com.example.notnik_kg.entities.UserEntity;
import com.example.notnik_kg.models.UserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserConfirmPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequest person = (UserRequest) target;

        if(person.getPassword().equals(person.getConfirmPass())){
            return;
        }else

        errors.rejectValue("password", "", "Passwords do not match");
    }
}
