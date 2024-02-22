package com.espark.adarsh.web;

import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Timed
    @GetMapping("/message")
    public String getMessage() {
        return "welcome from espark";
    }
}
