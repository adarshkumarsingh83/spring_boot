package com.espark.adarsh.service;

import com.espark.adarsh.exception.ServiceUnAvailableException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.Map;

public interface ApplicationService {

    @Retryable(value = {ServiceUnAvailableException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    Map<String, String> getWishMessage(String name);

    @Recover
    Map<String, String> getWishMessageFallback(ServiceUnAvailableException runtimeException);
}
