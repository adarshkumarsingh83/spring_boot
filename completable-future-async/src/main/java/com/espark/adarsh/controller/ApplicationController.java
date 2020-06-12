package com.espark.adarsh.controller;

import com.espark.adarsh.service.ApplicationServiceMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationServiceMain applicationServiceMain;

    @GetMapping(value = "/response")
    public List<List<Object>> getResponse(){
        return applicationServiceMain.getResponse();
    }
}
