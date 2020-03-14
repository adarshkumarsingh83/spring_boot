package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableEurekaClient //or @EnableDiscoveryClient
public class ProfileMicroService {

    public static void main(String[] args) {
        SpringApplication notificationMicroService = new SpringApplication(ProfileMicroService.class);
        notificationMicroService.addListeners(new ApplicationPidFileWriter("person-profile-ms.pid"));
        notificationMicroService.run(args);
    }
}
