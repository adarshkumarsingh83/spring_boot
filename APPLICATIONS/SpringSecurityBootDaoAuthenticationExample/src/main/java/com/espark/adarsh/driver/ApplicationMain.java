package com.espark.adarsh.driver;

import com.espark.adarsh.configuration.mvc.ApplicationConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.espark.adarsh")
@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}
