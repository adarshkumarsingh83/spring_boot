package com.espark.adarsh.web;

import com.espark.adarsh.service.CommunicationService;
import com.espark.adarsh.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    private final MyService myService;

    private final CommunicationService communicationService;

    public ApplicationController(CommunicationService communicationService,
                                 MyService myService) {
        this.communicationService = communicationService;
        this.myService = myService;
    }

    @GetMapping("/wish")
    public String getWishMessage() {
        String response = myService.getWish(System.getProperty("user.name"));
        log.info("Response from Controller : {}", response);
        return response;
    }

    @GetMapping("/communicate/message")
    public String communicateMessage() {
        String response = this.communicationService.communicate(System.getProperty("user.name"));
        log.info("Response from Controller : {}", response);
        return response;
    }
}
