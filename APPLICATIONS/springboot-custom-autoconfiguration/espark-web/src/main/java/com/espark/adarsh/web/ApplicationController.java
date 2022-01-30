package com.espark.adarsh.web;

import com.espark.adarsh.service.EsparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    EsparkService esparkService;

    @GetMapping("/wish")
    public String getWish() {
        return this.esparkService.getMessage();
    }
}
