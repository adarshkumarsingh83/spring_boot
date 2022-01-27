package com.espark.adarsh.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @GetMapping("/wish")
    public Map<String, String> getWish() {
        log.info("ApplicationController::getWish()");
        return new HashMap<>() {
            {
                put("message", "welcome to the espark ");
            }
        };
    }
}
