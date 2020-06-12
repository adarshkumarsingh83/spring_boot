package com.espark.adarsh.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    private final Logger logger= LoggerFactory.getLogger(ApplicationController.class);

    @Value("${message}")
    private String message;

    @RequestMapping(value = "/application/welcome"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, String> welcomeMessage() {
        final Map<String, String> msg = new HashMap<String, String>() {
            {
                put("name", System.getProperty("user.name"));
                put("msg", "Hello " + System.getProperty("user.name") + " " + message);
            }
        };
        logger.info("Request Received in Application Controller ");
        return msg;
    }
}
