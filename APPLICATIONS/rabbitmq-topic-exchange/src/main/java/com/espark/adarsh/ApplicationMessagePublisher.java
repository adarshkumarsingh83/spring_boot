package com.espark.adarsh;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.espark.adarsh.config.MessageReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationMessagePublisher implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final MessageReceiver receiver;

    @Value("${app.topicExchangeName}")
    String topicExchangeName;

    @Value("${app.routingKey}")
    String routingKey;

    public ApplicationMessagePublisher(MessageReceiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Publishing Message ");
        String msg = "Welcome to Espark Adarsh ";
        IntStream.range(0, 50)
                .boxed().filter(integer -> {
                    log.info(">>> Publishing Message " + msg + integer);
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (Exception e) {
                        log.info(e.getMessage());
                    }
                    return true;
                }).forEach(integer -> {
                    rabbitTemplate.convertAndSend(topicExchangeName, routingKey, msg + integer);
                });
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
