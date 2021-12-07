package com.espark.adarsh.config;

import java.util.concurrent.CountDownLatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        log.info("<<< Received Message {}", message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}