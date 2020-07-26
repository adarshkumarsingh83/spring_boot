package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EmbeddedHazelcastCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmbeddedHazelcastCacheApplication.class, args);
	}

}
