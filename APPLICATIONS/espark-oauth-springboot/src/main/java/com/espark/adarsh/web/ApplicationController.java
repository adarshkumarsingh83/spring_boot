package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    @GetMapping("/espark/api")
    public Map<String, String> message() {
        return new HashMap<>() {
            {
                put("message", "welcome to espark");
            }
        };
    }

}
