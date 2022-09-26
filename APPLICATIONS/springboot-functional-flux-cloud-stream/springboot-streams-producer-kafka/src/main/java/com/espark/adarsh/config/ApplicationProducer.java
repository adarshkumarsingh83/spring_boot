package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;

@Slf4j
@Configuration
public class ApplicationProducer {

    Random random = new Random();

    @Bean
    public Supplier<Flux<Integer>> producer() {
        return () -> Flux.interval(Duration.ofSeconds(1))
                .map(value -> random.nextInt(1000 - 1) + 1)
                .doOnEach((number) -> log.info("Producer {}", number));
    }
}
