package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EsparkController {

    @GetMapping("/wish")
    public String getWish() {
        return "welcome to the espark";
    }
}
