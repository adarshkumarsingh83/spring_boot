package com.espark.adarsh.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Slf4j
@Component
public class SqsListenerExceptionHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("label=handler exception={}", t.getLocalizedMessage());
    }
}


