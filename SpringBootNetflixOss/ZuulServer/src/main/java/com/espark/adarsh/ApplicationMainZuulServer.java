package com.espark.adarsh;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ApplicationMainZuulServer {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMainZuulServer.class, args);
    }
}
