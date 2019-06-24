package com.espark.adarsh.controller;

import com.espark.adarsh.entity.User;
import com.espark.adarsh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

   @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User create(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/user/{id}")
    public User update(@PathVariable("id")Long userId,@RequestBody User user){
        return userService.updateUser(userId,user);
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable("id") Long userId){
        return userService.getUser(userId).orElse(null);
    }

    @DeleteMapping("/user/{id}")
    public User delete(@PathVariable("id") Long userId){
        return userService.deleteById(userId);
    }
}
