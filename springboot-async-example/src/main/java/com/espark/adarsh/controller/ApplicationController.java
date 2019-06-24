package com.espark.adarsh.controller;

import com.espark.adarsh.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    // http://localhost:9090/application/10

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(value = "/application/{number}")
    public String startCounter(@PathVariable("number") long number) {

        this.applicationService.startCounter(number);
        return "Application Counter is started ";
    }
}
