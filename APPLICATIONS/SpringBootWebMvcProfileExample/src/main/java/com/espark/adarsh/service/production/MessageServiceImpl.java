
package com.espark.adarsh.service.production;

import com.espark.adarsh.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class MessageServiceImpl implements MessageService {

	@Value("${spring.profile.production.message}")
	private String message;

	@Override
	public String getMessage() {
		return this.message + " " +System.getProperty("user.name");
	}

}
