package com.espark.adarsh.autoconfigure;

import static com.espark.adarsh.GreeterConfigParams.AFTERNOON_MESSAGE;
import static com.espark.adarsh.GreeterConfigParams.EVENING_MESSAGE;
import static com.espark.adarsh.GreeterConfigParams.MORNING_MESSAGE;
import static com.espark.adarsh.GreeterConfigParams.NIGHT_MESSAGE;
import static com.espark.adarsh.GreeterConfigParams.USER_NAME;

import com.espark.adarsh.Greeter;
import com.espark.adarsh.GreeterProperties;
import com.espark.adarsh.GreetingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(GreeterProperties.class)
public class GreeterAutoConfiguration {

    @Autowired
    private GreeterProperties greeterProperties;

    @Bean
    @ConditionalOnMissingBean
    public GreetingConfig greeterConfig() {

        GreetingConfig greetingConfig = new GreetingConfig();
        String userName = greeterProperties.getUserName() == null
                ? System.getProperty("user.name")
                : greeterProperties.getUserName();
        greetingConfig.put(USER_NAME, userName);

        String morningMsg = greeterProperties.getMorningMessage();
        if (morningMsg != null && !morningMsg.isEmpty()) {
            greetingConfig.put(MORNING_MESSAGE, morningMsg);
        }

        String afternoonMsg = greeterProperties.getAfternoonMessage();
        if (afternoonMsg != null && !afternoonMsg.isEmpty()) {
            greetingConfig.put(AFTERNOON_MESSAGE, afternoonMsg);
        }

        String eveningMsg = greeterProperties.getEveningMessage();
        if (eveningMsg != null && !eveningMsg.isEmpty()) {
            greetingConfig.put(EVENING_MESSAGE, eveningMsg);
        }
        String nightMsg = greeterProperties.getNightMessage();
        if (nightMsg != null && !nightMsg.isEmpty()) {
            greetingConfig.put(NIGHT_MESSAGE, nightMsg);
        }
        return greetingConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public Greeter greeter(GreetingConfig greetingConfig) {
        return new Greeter(greetingConfig);
    }
}

