package com.espark.adarsh.rest;

import com.espark.adarsh.repository.JcrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @Autowired
    private JcrRepository jcrRepository;



}
