package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringBootRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRetryApplication.class, args);
	}

}
