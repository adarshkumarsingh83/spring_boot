package com.espark.adarsh.controller;

import com.espark.adarsh.feign.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApplicationController {

    @Autowired
    private ExternalService externalService;

    @RequestMapping(value = "/msg",method = RequestMethod.GET)
    public Map<String,String> getMessage(){
       return this.externalService.getMessage();
    }

}
