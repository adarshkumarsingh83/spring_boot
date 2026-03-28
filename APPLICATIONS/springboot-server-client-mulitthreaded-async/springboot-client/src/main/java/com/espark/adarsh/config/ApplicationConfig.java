package com.espark.adarsh.config;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.factory.PoolObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public PoolObjectFactory<User> getUserPoolObjectFactory(ApplicationProps applicationProps) {
        return new PoolObjectFactory<User>(applicationProps.getRequestCount(), () -> {
            try {
                return new User().clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
