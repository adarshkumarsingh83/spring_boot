package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Slf4j
@Configuration
public class ApplicationConsumer {

    @Bean
    public Consumer<Flux<String>> consumer() {
        return stringFlux -> stringFlux.subscribe(data -> log.info("Consumer {}", data));
    }

}
