package com.espark.adarsh.service;

import com.espark.adarsh.service.util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DefaultCommunicationService implements CommunicationService{

    private final ServiceUtil serviceUtil;

    @Override
    public String communicate(String message) {
        log.info("Default Message: {}", message);
        return "Default Message Sent: "+this.serviceUtil.getWishFunction().apply(message);
    }
}
