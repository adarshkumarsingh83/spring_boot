package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init(){
      log.info("ApplicationConfig.init() {}", Arrays.stream(environment.getActiveProfiles()).collect(Collectors.joining(", ")));
    }


}
