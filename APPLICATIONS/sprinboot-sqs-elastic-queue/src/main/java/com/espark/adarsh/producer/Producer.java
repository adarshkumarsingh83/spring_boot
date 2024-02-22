package com.espark.adarsh.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


@Component
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${amzSQSPublishEventListener.workDestination}")
    private String workDestination;

    @PostConstruct
    public void init(){
        sendMessage();
    }

   public void sendMessage(){
      int var = 10;
       while(var>0) {
           jmsTemplate.send(workDestination, new MessageCreator() {
               @Override
               public Message createMessage(Session session) throws JMSException {
                   return session.createObjectMessage("my message ");
               }
           });
           var--;
       }
   }

}
