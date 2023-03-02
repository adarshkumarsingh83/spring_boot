package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/wish/{type}/{name}")
    public Map<String, String> wish(@PathVariable("type") String type, @PathVariable("name") String name) {
        return this.applicationService.wish(type, name);
    }

}
