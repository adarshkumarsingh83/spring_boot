package com.espark.adarsh.configuration;

import com.espark.adarsh.feign.ExternalService;
import com.espark.adarsh.feign.FeignErrorDecoder;
import com.espark.adarsh.feign.FeignSLF4jLogger;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Value("${external.service.url}")
    private String externalServiceUrl;

    @Bean
    public ExternalService getExternalService() {
        return  Feign.builder()
                .client(new OkHttpClient())
                .logger(new FeignSLF4jLogger(ExternalService.class))
                .errorDecoder(new FeignErrorDecoder())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logLevel(Logger.Level.FULL)
                .target(ExternalService.class, externalServiceUrl);
    }

}
