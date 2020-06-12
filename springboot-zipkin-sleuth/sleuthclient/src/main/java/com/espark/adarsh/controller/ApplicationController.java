package com.espark.adarsh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private static Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @RequestMapping("/wish")
    public String home() {
        log.info("Handling home");
        return "Hello World";
    }


}
