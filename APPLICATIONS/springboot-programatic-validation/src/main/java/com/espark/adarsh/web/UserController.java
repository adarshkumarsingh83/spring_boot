package com.espark.adarsh.web;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/user")
    public String inputUser(@RequestBody User user){
        return this.userService.inputUser(user);
    }
}
