package com.espark.adarsh.web;

import com.espark.adarsh.service.EsparkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    @Autowired
    private EsparkService esparkService;

    @GetMapping("/message/{name}")
    public String getWish(@PathVariable("name") String name) {
        log.info("label=ApplicationController name={}", name);
        return this.esparkService.doOperation(name);
    }
}
