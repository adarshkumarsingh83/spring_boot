package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.espark.adarsh.filter.EsparkGatewayFilter;

@EnableZuulProxy
@SpringBootApplication
public class EsparkGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsparkGatewayApplication.class, args);
    }

    @Bean
    public EsparkGatewayFilter gateWayFilter() {
        return new EsparkGatewayFilter();
    }

}
