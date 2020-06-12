package com.espark.adarsh;

import com.espark.adarsh.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleProfileApplication implements CommandLineRunner {

	// Simple example shows how a command line spring application can execute an
	// injected bean service. Also demonstrates how you can use @Value to inject
	// command line args ('--name=whatever') or application properties
	@Autowired
	private MessageService helloWorldService;

	@Override
	public void run(String... args) {
		System.out.println("\n\n:==> "+this.helloWorldService.getMessage());
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleProfileApplication.class, args);
	}

}
