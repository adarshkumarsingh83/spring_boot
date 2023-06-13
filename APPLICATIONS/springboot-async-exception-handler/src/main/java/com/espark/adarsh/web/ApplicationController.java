package com.espark.adarsh.web;

import com.espark.adarsh.exception.ApplicationException;
import com.espark.adarsh.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/wish/{value}")
    public String doWork(@PathVariable Integer value) throws Exception {
        if (value == 0) {
            throw new ApplicationException("it's time for exception ");
        }
        this.applicationService.doWork();
        return "do work request accepted ";
    }
}
