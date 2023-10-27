package com.espark.adarsh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {


    @GetMapping("/")
    String home(){
        return "home";
    }
}
