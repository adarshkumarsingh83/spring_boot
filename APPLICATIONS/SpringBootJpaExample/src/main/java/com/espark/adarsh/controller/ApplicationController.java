package com.espark.adarsh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping(value="/")
    public String welcome() {
        return "index";
    }
}


