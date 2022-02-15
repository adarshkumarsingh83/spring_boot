package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    @GetMapping("/wish")
    public Map<String,String> getWishMessage(){
        return new HashMap<String,String>(){
            {
              put("message","welcome to espark ");
              put("time", LocalTime.now().toString());
            }
        };
    }
}
