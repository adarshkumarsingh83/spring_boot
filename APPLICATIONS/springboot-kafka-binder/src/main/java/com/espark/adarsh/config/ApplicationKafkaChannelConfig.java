package com.espark.adarsh.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ApplicationKafkaChannelConfig {

    String INPUT_CHANNEL = "espark-in-channel";
    String OUTPUT_CHANNEL = "espark-out-channel";
    String ERROR_CHANNEL = "error-out-channel";

    @Input(INPUT_CHANNEL)
    SubscribableChannel applicationSubscriberStream();

    @Output(OUTPUT_CHANNEL)
    MessageChannel applicationPublisherStream();

    @Output(ERROR_CHANNEL)
    MessageChannel applicationERRORStream();
}
