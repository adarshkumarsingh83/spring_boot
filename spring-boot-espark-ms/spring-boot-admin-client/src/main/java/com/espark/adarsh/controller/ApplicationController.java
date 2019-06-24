package com.espark.adarsh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping(value = "/health",method = RequestMethod.GET)
    public String health(){
        return "welcome to the application ";
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info(){
        return "welcome to the application ";
    }
}
