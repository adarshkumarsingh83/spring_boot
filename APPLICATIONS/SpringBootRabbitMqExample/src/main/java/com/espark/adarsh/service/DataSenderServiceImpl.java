package com.espark.adarsh.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

public class DataSenderServiceImpl implements DataSenderService{

    @Value("${app.route.key}")
    private String routeKey;

	@Autowired(required = true)
	private RabbitTemplate rabbitTemplate;

    @Override
	@Scheduled(fixedDelay = 1000L)
	public void send() {
		this.rabbitTemplate.convertAndSend(routeKey, "Hello "+System.getProperty("user.name"));
	}

}
