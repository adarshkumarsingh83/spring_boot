package com.espark.adarsh.controller;

@org.springframework.web.bind.annotation.RestController
public class ApplicationController {

    @org.springframework.web.bind.annotation.GetMapping(value = "/message/{name}")
    public String message(@org.springframework.web.bind.annotation.PathVariable("name")String name){
        return "Welcome to Esaprk "+name;
    }
}
