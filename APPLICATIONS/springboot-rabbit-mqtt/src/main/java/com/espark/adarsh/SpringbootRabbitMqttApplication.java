package com.espark.adarsh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Slf4j
@IntegrationComponentScan
@SpringBootApplication
public class SpringbootRabbitMqttApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitMqttApplication.class, args);
	}

}
