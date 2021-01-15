package com.espark.adarsh.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WishService {

    @RequestMapping(value = "/wish")
    public String wish() {
        return "Welcome To Espark";
    }

}
