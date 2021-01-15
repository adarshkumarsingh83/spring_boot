package com.espark.adarsh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class AuthServerApplicationMain {

    @Bean
    CommandLineRunner dbInit(UserBeanRepository userBeanRepository){
        return args -> {
            Stream.of("admin,admin","adarsh,adarsh")
                    .map(data -> data.split(","))
                    .forEach(user->userBeanRepository.save(new UserBean(user[0],user[1],true)));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplicationMain.class, args);
    }
}

