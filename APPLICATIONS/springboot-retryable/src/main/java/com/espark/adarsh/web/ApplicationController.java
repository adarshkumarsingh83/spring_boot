package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/message/{name}")
    public Map<String,String> getWishMessage(@PathVariable("name") String name) {
        return this.applicationService.getWishMessage(name);
    }
}
