package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @GetMapping("/wish")
    public String getWish() {
        return "Welcome to the Espark " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date());
    }
}
