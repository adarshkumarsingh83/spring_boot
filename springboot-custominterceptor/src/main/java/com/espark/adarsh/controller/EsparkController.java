package com.espark.adarsh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EsparkController {

    @GetMapping("/messge")
    public Map<String,String> getMessage(){
        return new HashMap<String,String>(){
            {
               put("adarsh","kumar");
               put("radha","singh");
            }
        };
    }

    @GetMapping("/wish")
    public Map<String,String> getWish(){
        return new HashMap<String,String>(){
            {
                put("adarsh","kumar");
                put("radha","singh");
            }
        };
    }
}
