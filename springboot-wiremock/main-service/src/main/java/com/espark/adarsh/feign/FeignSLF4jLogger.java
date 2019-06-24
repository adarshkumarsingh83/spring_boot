package com.espark.adarsh.feign;

import feign.slf4j.Slf4jLogger;

public class FeignSLF4jLogger extends Slf4jLogger {

    public FeignSLF4jLogger(Class<?> clazz) {
        super(clazz);
    }
}
