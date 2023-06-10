package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/wish")
    public CompletableFuture<String> wish(){
        return this.applicationService.wish();
    }
}
