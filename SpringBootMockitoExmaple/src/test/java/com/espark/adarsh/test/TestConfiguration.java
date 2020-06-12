package com.espark.adarsh.test;

import com.espark.adarsh.repository.PermissionRepository;
import com.espark.adarsh.repository.PermissionRepositoryImpl;
import com.espark.adarsh.repository.UserRepository;
import com.espark.adarsh.repository.UserRepositoryImpl;
import com.espark.adarsh.service.UserService;
import com.espark.adarsh.service.UserServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    public UserRepository getUserRepository() {
        return Mockito.mock(UserRepositoryImpl.class);
    }

    @Bean
    @Primary
    public PermissionRepository getPermissionRepository() {
        return Mockito.mock(PermissionRepositoryImpl.class);
    }

    @Bean
    @Primary
    public UserService getUserService() {
        return Mockito.mock(UserServiceImpl.class);
    }
}
