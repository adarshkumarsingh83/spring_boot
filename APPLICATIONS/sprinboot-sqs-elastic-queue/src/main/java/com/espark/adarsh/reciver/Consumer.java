package com.espark.adarsh.reciver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    @JmsListener(destination = "${amzSQSPublishEventListener.workDestination}")
    void onMessageReceived(String message) {
        try {
            System.out.println("Received: " +new String(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
