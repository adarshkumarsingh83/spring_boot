package com.espark.adarsh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceTwoImpl implements Services {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String myService() {
        logger.info("ServiceTwoImpl info ");
        logger.debug("ServiceTwoImpl debug ");
        logger.error("ServiceTwoImpl error ");
        return "Response from ServiceTwoImpl";
    }
}
