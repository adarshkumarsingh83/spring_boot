package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("/message")
    public String getMessage(){
        return "welcome from espark";
    }
}
