package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @GetMapping("/wish")
    public Map<String, String> wish() {
        return new HashMap<>() {
            {
                put("name", "adarsh kumar");
                put("msg", "welcome to the application");
            }
        };
    }

}
