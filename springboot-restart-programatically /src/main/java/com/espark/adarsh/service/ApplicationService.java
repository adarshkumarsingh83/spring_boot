package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationService {


    @Autowired
    private RestartEndpoint restartEndpoint;


    public void restartApplication() {
        log.info("label=ApplicationService.restartApplication() executing ");
        restartEndpoint.restart();
    }

}
