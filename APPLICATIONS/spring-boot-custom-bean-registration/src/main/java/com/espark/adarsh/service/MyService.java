package com.espark.adarsh.service;

import com.espark.adarsh.service.util.ServiceUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class MyService {

    private final ServiceUtil serviceUtil;

    public String getWish(String name) {
        String response = serviceUtil.getWishFunction().apply(name);
        log.info("Response from Service : {}", response);
        return response;
    }
}
