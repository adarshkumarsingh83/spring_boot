package com.espark.adarsh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WishServiceImpl implements WishService{

	@Value("${message:Welcome}")
	private String message;

    @Override
	public String getMessage() {
		return message+" "+System.getProperty("user.name");
	}

}
