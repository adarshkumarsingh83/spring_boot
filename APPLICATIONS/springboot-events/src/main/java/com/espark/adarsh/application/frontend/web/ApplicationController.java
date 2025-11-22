package com.espark.adarsh.application.frontend.web;

import com.espark.adarsh.application.frontend.bean.EventRequest;
import com.espark.adarsh.application.frontend.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/event")
    public String triggerEvent(@RequestBody EventRequest eventRequest) {
        eventRequest.setMessage(eventRequest.getMessage()+" "+ LocalDateTime.now().toString());
        log.info("Received request to trigger event with message: " + eventRequest.getMessage());
        return applicationService.processMessage(eventRequest.getMessage());
    }
}
