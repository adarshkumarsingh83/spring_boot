package com.espark.adarsh.service;

import com.espark.adarsh.service.util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EmailCommunicationService implements CommunicationService{

    private final ServiceUtil serviceUtil;

    @Override
    public String communicate(String message) {
        log.info("Sending Email Message: {}", message);
        return "Email Message Sent: "+this.serviceUtil.getWishFunction().apply(message);
    }
}
