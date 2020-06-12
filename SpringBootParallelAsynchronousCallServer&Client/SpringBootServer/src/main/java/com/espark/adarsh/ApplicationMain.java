package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.espark.adarsh")
@PropertySource("classpath:application.properties")
public class ApplicationMain {

    //http://localhost:9090/application/welcome
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationMain.class, args);
    }

}
