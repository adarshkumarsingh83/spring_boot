package com.espark.adarsh;

import com.espark.adarsh.service.GreetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@EnableConfigurationProperties
@SpringBootApplication
public class SpringbootStandaloneApplication implements CommandLineRunner {

    @Autowired
    GreetService greetService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStandaloneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(this.greetService.greet(System.getProperty("user.name")));
    }

}
