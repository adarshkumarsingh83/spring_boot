package com.espark.adarsh.emq;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import com.espark.adarsh.emq.ElasticMQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.ConnectionFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration
public class ElasicMqConfiguration {



    @Value("${elastic.mq.name}")
    private String[] queueNamesArray ;

    @Value("${elastic.mq.port}")
    private int mqPort = 9325;

    @Value("${elastic.mq.host}")
    private String mqHost;

    @Value("${elastic.mq.region}")
    private String region;

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    private List<String> queueNames;

    private  Map<String, ElasticMQ> queueMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        queueNames = Arrays.asList(queueNamesArray);
        AmazonSQS client = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(mqHost+mqPort, region))
                .build();
        for (String queueName : queueNames) {
            client.createQueue(queueName);
            ElasticMQ sqsQueue = new ElasticMQ(client,client.getQueueUrl(new GetQueueUrlRequest(queueName)).getQueueUrl());
            queueMap.put(queueName, sqsQueue);
        }
    }
    
    @Bean
    ConnectionFactory mqConnectionFactory() {
        AmazonSQSClient amazonSQSClient = new AmazonSQSClient(new BasicAWSCredentials("x", "x"));
        amazonSQSClient.setEndpoint("http://localhost:" + mqPort);
        return new SQSConnectionFactory(new ProviderConfiguration(), amazonSQSClient);
    }

    @PreDestroy
    void destroy() {
        queueMap.entrySet().forEach( (Map.Entry<String,ElasticMQ> entry) -> {
            ElasticMQ sqsQueue=entry.getValue();
            sqsQueue.purge();
            System.out.println(sqsQueue);
        });
    }

    public ElasticMQ getSqsQueue(String queueName){
        return this.queueMap.get(queueName);
    }

    public Map<String, ElasticMQ> getQueueMap() {
        return queueMap;
    }
}



