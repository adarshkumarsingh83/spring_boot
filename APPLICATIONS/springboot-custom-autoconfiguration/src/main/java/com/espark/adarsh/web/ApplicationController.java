package com.espark.adarsh.web;

import com.espark.adarsh.service.EsparkApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    EsparkApplicationService applicationService;


    @GetMapping("/wish/{name}")
    public String getGreeting(@PathVariable("name")String name){
        return this.applicationService.displayGreeting.apply(name);
    }
}
