package com.espark.adarsh.config;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticmq.rest.sqs.SQSRestServer;
import org.elasticmq.rest.sqs.SQSRestServerBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class ElasticMqConfig {

    private SQSRestServer server;
    private static final String endpoint = "http://localhost:9324";

    @PostConstruct
    public void init() {
        log.info("label=init() executed ");
        server = SQSRestServerBuilder.withPort(9324).withInterface("localhost").start();
        AmazonSQSClient amazonSQSClient = new AmazonSQSClient(new BasicAWSCredentials("x", "x"));
        amazonSQSClient.setEndpoint(endpoint);
        amazonSQSClient.createQueue("WORK-LOCAL-CONSUMING");
        amazonSQSClient.createQueue("WORK-LOCAL-PRODUCING");
    }

    @PreDestroy
    public void destroy() {
        log.info("label=destroy() executed ");
        server.stopAndGetFuture();
    }
}
