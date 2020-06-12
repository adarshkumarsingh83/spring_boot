package com.espark.adarsh.controller;

import com.espark.adarsh.Greeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/espark")
public class WishController {

    @Autowired
    private Greeter greeter;

    @GetMapping("/wish")
    public String getWish() {
        String message = greeter.greet();
        return message;
    }
}
