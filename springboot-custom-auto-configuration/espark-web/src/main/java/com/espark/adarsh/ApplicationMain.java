package com.espark.adarsh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class ApplicationMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationMain.class, args);
		Arrays.asList(applicationContext.getBeanDefinitionNames()).stream().forEach(log::info);

	}

}
