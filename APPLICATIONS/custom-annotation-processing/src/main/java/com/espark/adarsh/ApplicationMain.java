package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.espark.adarsh")
public class ApplicationMain {

	public static void main(String[] args) {
		String[] appArgs = {"--debug"};
		SpringApplication.run(ApplicationMain.class, appArgs);
	}
}
