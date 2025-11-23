package com.espark.adarsh.service;

import com.espark.adarsh.service.util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SmsCommunicationService implements CommunicationService{

    private final ServiceUtil serviceUtil;

    @Override
    public String communicate(String message) {
        log.info("Sending SMS Message: {}", message);
        return "SMS Message Sent: "+this.serviceUtil.getWishFunction().apply(message);
    }
}
