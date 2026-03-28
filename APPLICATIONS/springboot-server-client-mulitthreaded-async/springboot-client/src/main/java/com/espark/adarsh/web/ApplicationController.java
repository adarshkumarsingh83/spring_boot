package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import com.espark.adarsh.service.JobProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
public class ApplicationController {

    JobProcessingService jobProcessingService;

    public ApplicationController(JobProcessingService jobProcessingService) {
        this.jobProcessingService = jobProcessingService;
    }

    @GetMapping("/request/{number}")
    public String sendRequests(@org.springframework.web.bind.annotation.PathVariable int number) throws InterruptedException {
        log.info("Received home request to send {} requests", number);
        return this.jobProcessingService.startJob(number);
    }
}
