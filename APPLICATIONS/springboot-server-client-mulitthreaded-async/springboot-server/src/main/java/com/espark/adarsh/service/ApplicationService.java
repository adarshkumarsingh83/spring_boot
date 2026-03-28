package com.espark.adarsh.service;

import com.espark.adarsh.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@Service
public class ApplicationService {

    public String wishUser(User user) {
        log.info("Received wish request for user: {}", user);
        long delayMillis = ThreadLocalRandom.current().nextLong(100, 900); // 1..10 inclusive
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Sleep interrupted", e);
        }
        return "Hello, " + user + "! Welcome to our application.";
    }
}
