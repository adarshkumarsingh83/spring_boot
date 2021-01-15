

package com.espark.adarsh.service.testing;

import com.espark.adarsh.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("testing")
public class MessageServiceImpl implements MessageService {

    @Value("${spring.profile.testing.message}")
    private String message;

    @Override
    public String getMessage() {
        return this.message + " " + System.getProperty("user.name");
    }

}
