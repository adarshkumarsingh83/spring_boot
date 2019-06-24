package com.espark.adarsh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    @GetMapping("/message")
    public Map<String,String> getWish(){
        return new HashMap<String,String>(){
            {
              put("message","welcome to espark");
            }
        };
    }
}
