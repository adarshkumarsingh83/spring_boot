package com.espark.adarsh.evernt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobEventPublisher {

    private final ApplicationEventPublisher publisher;

    public JobEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public String publishEvent(JobEvent jobEvent){
        publisher.publishEvent(jobEvent);
        log.info("Published Event {}",jobEvent);
        return "done";
    }
}
