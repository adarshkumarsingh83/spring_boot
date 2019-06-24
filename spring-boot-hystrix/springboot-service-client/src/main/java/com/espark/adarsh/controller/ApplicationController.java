package com.espark.adarsh.controller;

import com.espark.adarsh.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {


    @Autowired
    private ClientService clientService;

    @GetMapping("/message/{username}")
    public String greeting(@PathVariable("username") String username) {
        return this.clientService.greeting(username);
    }
}
