package com.espark.adarsh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceOneImpl implements Services {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String myService() {
          logger.info("ServiceOneImpl info ");
          logger.debug("ServiceOneImpl debug ");
          logger.error("ServiceOneImpl error ");
        return "Response from ServiceOneImpl";
    }
}
