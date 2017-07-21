package com.espark.adarsh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Value("${message:welcome}")
	private String message;

    @Override
	public String getMessage() {
		return this.message+" "+System.getProperty("user.name");
	}
}
