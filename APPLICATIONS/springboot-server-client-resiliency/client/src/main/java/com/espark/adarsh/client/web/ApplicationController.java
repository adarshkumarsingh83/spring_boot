package com.espark.adarsh.client.web;


import com.espark.adarsh.client.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping("/client-request/{option}")
    public ResponseEntity<String> processRequest(@PathVariable String option){
        log.info("ApplicationController :: processRequest {}",option);
        return this.applicationService.processRequest.apply(option);
    }
}
