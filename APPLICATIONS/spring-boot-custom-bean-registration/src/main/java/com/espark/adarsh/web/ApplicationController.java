package com.espark.adarsh.web;

import com.espark.adarsh.service.CommunicationService;
import com.espark.adarsh.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class ApplicationController {

    private final MyService myService;

    private final Map<String,CommunicationService> communicationServices;

    public ApplicationController(Map<String,CommunicationService> communicationServices,
                                 MyService myService) {
        this.communicationServices = communicationServices;
        this.myService = myService;
    }

    @GetMapping("/wish")
    public String getWishMessage() {
        String response = myService.getWish(System.getProperty("user.name"));
        log.info("Response from Controller : {}", response);
        return response;
    }

    @GetMapping("/communicate/message")
    public String communicateMessage(@RequestParam MultiValueMap<String, String> params ) {

        CommunicationService communicationService = this.communicationServices.get("default");
        if(params!=null && !params.isEmpty()){
            if(communicationServices.containsKey(params.get("type").get(0))){
                communicationService = this.communicationServices.get(params.get("type").get(0));
            }
        }
        String response = communicationService.communicate(System.getProperty("user.name"));
        log.info("Response from Controller : {}", response);
        return response;
    }
}
