package com.espark.adarsh.web;

import com.espark.adarsh.service.SslIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    SslIntegrationService sslIntegrationService;

    @GetMapping(value = "/list")
    public List<String> getApiList() {
        log.info("label=ApplicationController getApiList()");
        return this.sslIntegrationService.getServiceKey();
    }

    @GetMapping(value = "/service/{key}")
    public HashMap getServiceData(@PathVariable("key") String key) {
        log.info("label=ApplicationController getServiceData()");
        return this.sslIntegrationService.getData(key);
    }
}
