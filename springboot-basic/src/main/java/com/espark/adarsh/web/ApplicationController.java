package com.espark.adarsh.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApplicationController {


    @Value("${message}")
    private String message;

    @GetMapping(value = "/welcome"
            , produces = {"application/json"})
    public Map<String, String> welcomeMessage() {
        final Map<String, String> responseMessage = new HashMap<String, String>() {
            {
                put("name", System.getProperty("user.name"));
                put("responseMessage", "Hello " + System.getProperty("user.name") + " " + message);
            }
        };
        return responseMessage;
    }

}
