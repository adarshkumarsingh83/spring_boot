package com.espark.adarsh;

import com.espark.adarsh.config.ApplicationProperties;
import com.espark.adarsh.service.GreetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SpringbootStandaloneApplicationTests {

    @Autowired
    GreetService greetService;

    @Autowired
    ApplicationProperties applicationProperties;

    String serviceResponse;

    @BeforeEach
    public void init() {
        serviceResponse = applicationProperties.getMessage("wish") + " " + System.getProperty("user.name");
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testGreetService() {
        String actualResponse = this.greetService.greet(System.getProperty("user.name"));
        Assert.hasText(actualResponse,serviceResponse);
    }
}
