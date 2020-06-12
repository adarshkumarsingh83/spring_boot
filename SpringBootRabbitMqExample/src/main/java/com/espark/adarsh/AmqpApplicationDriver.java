package com.espark.adarsh;

import com.espark.adarsh.service.DataSenderServiceImpl;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@SpringBootApplication
@RabbitListener(queues = "espark")
@EnableScheduling
public class AmqpApplicationDriver {


    @Value("${app.route.queue}")
    private String queue;

	@Bean
	public DataSenderServiceImpl mySender() {
		return new DataSenderServiceImpl();
	}

	@Bean
	public Queue fooQueue() {
		return new Queue(queue);
	}

	@RabbitHandler
	public void process(@Payload String data) {
		System.out.println(new Date() + ": " + data);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AmqpApplicationDriver.class, args);
	}

}
