package com.espark.adarsh.test;


import com.espark.adarsh.consumer.Receiver;
import com.espark.adarsh.producer.Sender;

import java.util.concurrent.TimeUnit;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringKafkaApplicationTest.class);

    private static String BOOT_TOPIC = "boot.t";

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, BOOT_TOPIC);

    @Test
    public void testSendAndReceive() throws Exception {
        LOGGER.info("Method Execution Start()");
        for (int i = 0; i < 10; i++) {
            sender.send(BOOT_TOPIC, "Hello Kafka From Spring boot " + i);

            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
            assertThat(receiver.getLatch().getCount()).isEqualTo(0);
        }
        LOGGER.info("Method Execution End()");
    }
}
