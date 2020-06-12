package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/restart")
    public String ApplicationController() {
        log.info("label=ApplicationController.ApplicationController() executing ");
        this.applicationService.restartApplication();
        return "RESTARTING APPLICATION SHORTLY ";
    }
}
