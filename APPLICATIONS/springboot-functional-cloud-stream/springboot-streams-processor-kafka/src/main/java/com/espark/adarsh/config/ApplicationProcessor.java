package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class ApplicationProcessor {


    @Bean
    public Function<Flux<Integer>, Flux<String>> processor() {
        return longFlux -> longFlux.map(data -> eval(data))
                .doOnEach((data) -> log.info("Processor {}", data));
    }

    private String eval(Integer value) {
        if (value % 2 == 0) {
            return "EVEN " + value;
        } else {
            return "ODD " + value;
        }
    }
}
