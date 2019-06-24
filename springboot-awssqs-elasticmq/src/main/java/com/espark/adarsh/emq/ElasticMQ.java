package com.espark.adarsh.emq;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;

import java.util.List;

public class ElasticMQ {

    private AmazonSQS client;
    private String queueUrl;

    public ElasticMQ(AmazonSQS client, String queueUrl) {
        this.client = client;
        this.queueUrl = queueUrl;
    }

    public void send(Message toSend) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, toSend.getBody());
        sendMessageRequest.setMessageAttributes(toSend.getMessageAttributes());
        client.sendMessage(sendMessageRequest);
    }

    public List<Message> read(int maxMessages) {
        ReceiveMessageRequest request = new ReceiveMessageRequest(queueUrl);
        request.setMaxNumberOfMessages(maxMessages);
        ReceiveMessageResult receiveMessage = client.receiveMessage(request);
        return receiveMessage.getMessages();
    }

    public void purge() {
        client.purgeQueue(new PurgeQueueRequest(queueUrl));
    }
}
