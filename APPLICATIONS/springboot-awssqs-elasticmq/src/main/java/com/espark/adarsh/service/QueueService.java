package com.espark.adarsh.service;

import com.amazonaws.services.sqs.model.Message;
import com.espark.adarsh.emq.ElasicMqConfiguration;
import com.espark.adarsh.emq.ElasticMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class QueueService {

    @Autowired
    private ElasicMqConfiguration elasicMqConfiguration;

    private Map<String, ElasticMQ> queueMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        queueMap=this.elasicMqConfiguration.getQueueMap();
    }

    public void sendMessage(String queueName,String data){
        Message message= new Message();
        message.setBody(data);
        message.setMessageId(new Date().toString());
        queueMap.get(queueName).send(message);
    }

    public List<String> getMessage(String queueName){
        List<Message> messageList=queueMap.get(queueName).read(5);
        return messageList.stream().map(message->{
            return message.getBody();
        }).collect(Collectors.toList());
    }

}
