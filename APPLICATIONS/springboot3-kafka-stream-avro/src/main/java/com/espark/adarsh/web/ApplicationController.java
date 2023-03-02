package com.espark.adarsh.web;

import com.espark.adarsh.entities.Employee;
import com.espark.adarsh.producer.ApplicationDataProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    @Autowired
    ApplicationDataProducer applicationDataProducer;

    @PostMapping("/api/employee")
    public String publishData(@RequestBody Employee employee) {
        log.info("ApplicationController::publishData");
        return this.applicationDataProducer.publishData(employee);
    }
}
