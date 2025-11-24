package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

@EnableResilientMethods
@SpringBootApplication
public class SprinbootRestHttclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprinbootRestHttclientApplication.class, args);
	}

}
