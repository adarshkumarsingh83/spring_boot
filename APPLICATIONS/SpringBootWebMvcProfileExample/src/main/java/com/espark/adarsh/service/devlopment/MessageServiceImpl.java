
package com.espark.adarsh.service.devlopment;

import com.espark.adarsh.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("development")
public class MessageServiceImpl implements MessageService {

	@Value("${spring.profile.development.message}")
	private String message;

	@Override
	public String getMessage() {
		return this.message + " " +System.getProperty("user.name");
	}

}
