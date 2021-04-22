package com.espark.adarsh.web;

import com.espark.adarsh.bean.PrototypeSampleBean;
import com.espark.adarsh.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/instantiate/{number}")
    public String operator(@PathVariable("number") Integer number) throws InterruptedException {
        applicationService.operator(number);
        return "operation started with number " + number;
    }
}
