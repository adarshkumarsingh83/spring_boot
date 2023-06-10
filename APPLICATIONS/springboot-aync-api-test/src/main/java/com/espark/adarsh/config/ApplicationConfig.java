package com.espark.adarsh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    public ApplicationConfig() {
    }

    @Override
   public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
      configurer.setTaskExecutor(taskExecutor());
      configurer.setDefaultTimeout(30_000);
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("app-task");
        return taskExecutor;
    }
}
