package com.espark.adarsh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        // Example: use a custom header "x-api-version"
        //configurer.useRequestHeader("x-api-version");

        // You can also set a default version
        configurer.setDefaultVersion("1.0");

        // Example: to use a query parameter instead of a header
         configurer.useQueryParam("v");
    }
}