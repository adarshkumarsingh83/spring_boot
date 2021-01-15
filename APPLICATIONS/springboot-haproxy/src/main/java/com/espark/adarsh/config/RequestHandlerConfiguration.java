package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class RequestHandlerConfiguration implements WebMvcConfigurer {

    @Autowired
    RequestHandlerInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.debug("label=RequestHandlerConfiguration addInterceptors()");
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);
    }
}