package com.espark.adarsh.service;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Adarsh on 1/30/16.
 */
public interface DataSenderService {
    @Scheduled(fixedDelay = 1000L)
    void send();
}
