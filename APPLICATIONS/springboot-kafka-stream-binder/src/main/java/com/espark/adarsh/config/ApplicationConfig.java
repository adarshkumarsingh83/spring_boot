package com.espark.adarsh.config;

import com.espark.adarsh.bean.Message;
import com.espark.adarsh.util.TimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class ApplicationConfig {

    public final static String OUT_CHANNEL = "kafkaApplicationMessageProducer-out-0";
    public final static String IN_CHANNEL = "kafkaApplicationMessageConsumer-out-0";


    @Bean
    public Consumer<Message> kafkaApplicationMessageConsumer() {
        return message -> System.out.println("received " + message);
    }

    @Bean
    public Supplier<Message> kafkaApplicationMessageProducer(TimeUtil timeUtil) {
        return () -> new Message("SUPPLIER-> Welcome to the Espark " + timeUtil.getTime());
    }
}
