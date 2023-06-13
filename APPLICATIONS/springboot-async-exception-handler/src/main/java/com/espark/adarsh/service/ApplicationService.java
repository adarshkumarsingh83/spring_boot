package com.espark.adarsh.service;

import com.espark.adarsh.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationService {

    @Async
    public void doWork() throws InterruptedException {
        int counter = 0;
        while (counter < 100) {
            log.info("value of counter is {} Thread Name {}", counter, Thread.currentThread().getName());
            Thread.sleep(1000);
            if (counter == 10) {
                throw new ApplicationException("10 VALUE is not a good value ");
            }
            counter++;
        }
    }

}
