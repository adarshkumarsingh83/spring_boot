package com.espark.adarsh;

import com.espark.adarsh.config.ApplicationKafkaChannelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ApplicationKafkaChannelConfig.class)
@SpringBootApplication
public class SpringbootKafkaBinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaBinderApplication.class, args);
	}

}
