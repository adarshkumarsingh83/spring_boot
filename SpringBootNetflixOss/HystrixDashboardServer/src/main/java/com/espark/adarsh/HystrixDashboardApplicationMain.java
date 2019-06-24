package com.espark.adarsh;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableTurbine
public class HystrixDashboardApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplicationMain.class, args);
    }
}
