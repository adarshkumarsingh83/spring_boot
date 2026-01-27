package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
public class ApplicationController {

    ApplicationService applicationService;

    ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/request/{number}")
    public String sendRequests(@org.springframework.web.bind.annotation.PathVariable int number) throws InterruptedException {
        log.info("Received home request to send {} requests", number);
        this.applicationService.sendRequest(number);
        return "Requests are being processed asynchronously.for " + number + " users.";
    }
}
