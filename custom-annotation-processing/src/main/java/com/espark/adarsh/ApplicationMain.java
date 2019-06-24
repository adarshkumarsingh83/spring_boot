package com.espark.adarsh;

import com.espark.adarsh.annotation.EnableEspark;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.espark.adarsh.*")
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@EnableEspark
public class ApplicationMain {

	public static void main(String[] args) {
		String[] appArgs = {"--debug"};
		SpringApplication.run(ApplicationMain.class, appArgs);
	}

}
