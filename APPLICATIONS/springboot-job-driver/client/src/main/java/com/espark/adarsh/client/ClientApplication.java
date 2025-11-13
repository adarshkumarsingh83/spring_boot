package com.espark.adarsh.client;

import com.espark.adarsh.client.util.input.ApplicationProcessor;
import com.espark.adarsh.client.util.input.InputGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

import java.util.Map;

@Slf4j
@EnableRetry
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ClientApplication.class, args);
        InputGenerator inputGenerator = applicationContext.getBean(InputGenerator.class);
        Map<String, String> inputMap = inputGenerator.generateCmdLineInput.apply(args);
        log.info("Input Received to Process {}", inputMap);
        ApplicationProcessor applicationProcessor = applicationContext.getBean(ApplicationProcessor.class);
        Integer finalResult = applicationProcessor.getExecuteProcess().apply(inputMap);
        log.info("Final Status of the Request {} status {}", inputMap, finalResult);
        System.exit(finalResult);
    }

}
