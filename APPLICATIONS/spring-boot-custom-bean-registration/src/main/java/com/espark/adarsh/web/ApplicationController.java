package com.espark.adarsh.web;

import com.espark.adarsh.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    private MyService myService;

    public ApplicationController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/wish")
    public String getWishMessage() {
        String response = myService.getWish(System.getProperty("user.name"));
        log.info("Response from Controller : {}", response);
        return response;
    }
}
