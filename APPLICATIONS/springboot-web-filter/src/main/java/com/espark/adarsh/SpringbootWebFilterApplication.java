package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootWebFilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebFilterApplication.class, args);
	}

}
