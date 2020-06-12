package com.espark.adarsh;

import com.espark.adarsh.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AopApplicationDriver implements CommandLineRunner {

	@Autowired
	private MessageService messageService;

	@Override
	public void run(String... args) {
		System.out.println(this.messageService.getMessage());
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AopApplicationDriver.class, args);
	}

}
