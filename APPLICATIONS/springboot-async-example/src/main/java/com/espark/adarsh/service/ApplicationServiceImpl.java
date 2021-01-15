package com.espark.adarsh.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Async
    @Override
    public void startCounter(long max) {

        try {
            while (max > 0) {
                Thread.currentThread().sleep(1000);
                System.out.println(max);
                max--;
            }
        } catch (InterruptedException ae) {
            ae.printStackTrace();
        } finally {
            System.out.println("counter execution completed");
        }
    }
}
