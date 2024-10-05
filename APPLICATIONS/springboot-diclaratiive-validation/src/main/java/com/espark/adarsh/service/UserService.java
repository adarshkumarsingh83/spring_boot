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

    public String inputUser(User user){
       return user.toString();
    }
}
