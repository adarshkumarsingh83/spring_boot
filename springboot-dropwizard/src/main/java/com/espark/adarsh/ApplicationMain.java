package com.espark.adarsh;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMetrics(proxyTargetClass = true)
public class ApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

}
