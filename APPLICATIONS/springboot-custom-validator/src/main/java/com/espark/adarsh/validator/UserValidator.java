package com.espark.adarsh.validator;

import com.espark.adarsh.annotation.UserValidation;
import com.espark.adarsh.bean.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserValidator implements ConstraintValidator<UserValidation, User> {


    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {

        //todo logic for validating user
        log.info("UserValidation isValid {}",user);
        return false;
    }

    @Override
    public void initialize(UserValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
