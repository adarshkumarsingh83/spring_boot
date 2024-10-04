package com.espark.adarsh.service;

import com.espark.adarsh.bean.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    Validator validator;

    public String inputUser(User user){
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String result = violations.stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return  result.isEmpty() ? user.toString() : result;
    }
}
