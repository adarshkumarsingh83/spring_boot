package com.espark.adarsh;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMetrics(proxyTargetClass = true)
public class Springboot3ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot3ExampleApplication.class, args);
	}

}
