package com.espark.adarsh.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.internal.BasicProfile;
import com.amazonaws.auth.profile.internal.ProfileStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.elasticmq.rest.sqs.SQSRestServer;
import org.elasticmq.rest.sqs.SQSRestServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import java.util.HashMap;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${aws.sqsAccessKeyId}")
    String sqsAwsAccessKeyId;

    @Value("${aws.sqsSecretAccessKey}")
    String sqsAwsSecretAccessKey;

    @Value("${amzSQSPublishEventListener.workDestination}")
    private String workDestination;

    @Value("${elastic.server.url}")
    public String mqEndpoint;

    private SQSRestServer server;

    @PostConstruct
    public void init() {
        server = SQSRestServerBuilder.withPort(9324).withInterface("localhost").start();
        AmazonSQSClient amazonSQSClient = new AmazonSQSClient(new BasicAWSCredentials("x", "x"));
        amazonSQSClient.setEndpoint(mqEndpoint);
        amazonSQSClient.createQueue(workDestination);

    }

    @Profile("dev")
    @Bean(name = "connection")
    ConnectionFactory sqsConnectionFactory() {
        return new SQSConnectionFactory(new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard()
                        .withRegion(Regions.US_EAST_1)
                        .withCredentials(new ProfileStaticCredentialsProvider(new BasicProfile("default",
                                new HashMap() {
                                    {
                                        put("aws_access_key_id", sqsAwsAccessKeyId);
                                        put("aws_secret_access_key", sqsAwsSecretAccessKey);
                                    }
                                }
                        ))));
    }

    @Profile("local")
    @Bean(name = "connection")
    ConnectionFactory mqConnectionFactory() {
        AmazonSQSClient amazonSQSClient = new AmazonSQSClient(new BasicAWSCredentials("x", "x"));
        amazonSQSClient.setEndpoint(mqEndpoint);
        return new SQSConnectionFactory(new ProviderConfiguration(), amazonSQSClient);
    }

    @Bean
    @Autowired
    JmsTemplate sqsJmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

    @Bean
    @Autowired
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(@Qualifier("connection") ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @PreDestroy
    public void destroy() {
        server.stopAndGetFuture();
    }

}
