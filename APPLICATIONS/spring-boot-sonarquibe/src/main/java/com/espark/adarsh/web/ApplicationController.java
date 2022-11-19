package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @GetMapping("/wish/{name}")
    public Map<String,String> getWish(@PathVariable("name") String name){
        LocalDateTime localDateTime = LocalDateTime.now();

        return  new HashMap<String, String>() {
            {
                put("name","welcome to espark "+name);
                put("connected at ",localDateTime.getHour()+":"+localDateTime.getMinute());
            }
        };
    }
}
