package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    final static Logger log = LoggerFactory.getLogger(ApplicationController.class);

    ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/submit-data/{option}")
    public String submitDataProcessing(@PathVariable("option") Integer option) {
        log.info("ApplicationController::submitDataProcessing ");
        switch (option) {
            case 1 -> this.applicationService.postEmployees();
            case 2 -> this.applicationService.postEmployeeWithCounter();
            case 3 -> this.applicationService.postEmployeeData();
            default -> log.info("Invalid Option ");
        }
        return "Data Submitted for Processing";
    }
}
