package com.espark.adarsh.web;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApplicationController {

    ApplicationService applicationService;

    ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/api")
    public String wishUser(@RequestBody User user ) {
        log.info("Received request for user: {}", user);
        String response =  applicationService.wishUser(user);
        log.info("Response Generated : {}", response);
        return response;
    }
}
