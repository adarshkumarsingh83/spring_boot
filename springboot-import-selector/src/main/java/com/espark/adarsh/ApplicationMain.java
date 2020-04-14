package com.espark.adarsh;

import com.espark.adarsh.configuration.ApplicationConfiguration;
import com.espark.adarsh.service.ApplicationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        ApplicationConfiguration.class);
        ApplicationService bean = context.getBean(ApplicationService.class);
        System.out.println(bean.getMessage());
    }

}
