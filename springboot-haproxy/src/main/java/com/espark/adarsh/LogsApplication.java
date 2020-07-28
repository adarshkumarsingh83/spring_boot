package com.espark.adarsh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogsApplication.class, args);
        log.info("label=LogsApplication main()");
    }

}
