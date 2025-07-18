package com.espark.adarsh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import static com.espark.adarsh.util.Constants.SERVER_SEND_EVENT_EXECUTOR;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class ApplicationConfiguration implements AsyncConfigurer {

    @Bean(name = SERVER_SEND_EVENT_EXECUTOR)
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Server Send Event Thread  - ");
        executor.initialize();
        return executor;
    }
}
