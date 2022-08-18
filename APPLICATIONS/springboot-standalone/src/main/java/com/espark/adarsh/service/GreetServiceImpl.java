package com.espark.adarsh.service;

import com.espark.adarsh.config.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GreetServiceImpl implements GreetService {

    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public String greet(String name) {
        log.info(applicationProperties.getMessage("wish") + " " + name);
        return applicationProperties.getMessage("wish") + " " + name;
    }
}
