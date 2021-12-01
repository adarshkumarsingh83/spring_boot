package com.espark.adarsh.service;

import com.espark.adarsh.exception.ServiceUnAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    static int randomValue = 0;

    @Override
    public Map<String, String> getWishMessage(String name) {
        log.info("ApplicationServiceImpl.getWishMessage()");

        if(randomValue==0) {
            randomValue = new Random().nextInt(10);
            log.info("Random Value "+randomValue);
        }

        if (randomValue % 2 == 0) {
            throw new ServiceUnAvailableException("MESSAGES SERVICE IS DOWN ");
        }
        randomValue = 0;
        return new HashMap<String, String>() {
            {
                put("message", "WELCOME TO THE ESPARK WITH " + name + " USER");
            }
        };

    }

    @Override
    public Map<String, String> getWishMessageFallback(ServiceUnAvailableException runtimeException) {
        log.info("ApplicationServiceImpl.getWishMessageFallback()");
        randomValue = 0;
        return new HashMap<String, String>() {
            {
                put("message", "WELCOME TO THE ESPARK WITH ANONYMOUS USER");
                put("exception", runtimeException.getMessage());
            }
        };
    }
}
