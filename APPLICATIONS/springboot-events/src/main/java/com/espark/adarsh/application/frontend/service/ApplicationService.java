package com.espark.adarsh.application.frontend.service;

import com.espark.adarsh.application.frontend.event.EsparkEvent;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Slf4j
@Getter
@Service
public class ApplicationService {

   private ApplicationEventPublisher applicationEventPublisher;

    public ApplicationService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public String processMessage (String message) {
        applicationEventPublisher.publishEvent(new EsparkEvent(message));
        log.info("Event Published with message: {}" , message);
        return "Processed Message: " + message;
    };
}
