package com.espark.adarsh.controller;

import com.espark.adarsh.service.Services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApplicationController {


    @Autowired
    Services serviceOneImpl;

    @Autowired
    Services serviceTwoImpl;


    @RequestMapping(value = "/trace/{serviceName}", method = RequestMethod.GET)
    public String invokeService(@PathVariable("serviceName") String serviceName) {
        log.info("Invoking Service {}", serviceName);

        if (serviceName.equalsIgnoreCase("ServiceOneImpl")) {
            return this.serviceOneImpl.myService();
        } else if (serviceName.equalsIgnoreCase("ServiceTwoImpl")) {
            return this.serviceTwoImpl.myService();
        }
        return null;
    }


}
