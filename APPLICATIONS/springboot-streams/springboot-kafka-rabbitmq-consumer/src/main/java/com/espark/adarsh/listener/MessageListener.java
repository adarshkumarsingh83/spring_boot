package com.espark.adarsh.listener;

import com.espark.adarsh.bean.MessageBean;
import com.espark.adarsh.config.MessageStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@EnableBinding(MessageStreams.class)
public class MessageListener {

    @StreamListener(MessageStreams.INPUT)
    public void handleMessageBean(MessageBean messageBean) {
        log.info("Received messageBean: {}", messageBean);
    }
}
