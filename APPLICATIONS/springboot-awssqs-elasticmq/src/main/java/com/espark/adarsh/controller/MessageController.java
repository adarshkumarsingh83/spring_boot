package com.espark.adarsh.controller;

import com.espark.adarsh.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    QueueService queueService;

    @RequestMapping(value = "/espark/msg",method = RequestMethod.POST)
    public void sendMesage(@RequestBody Map<String,String > data){
        this.queueService.sendMessage(data.get("queueName"),data.get("msg"));
    }


    @RequestMapping(value = "/espark/msg/{queueName}",method = RequestMethod.GET)
    public List<String> recivedMesage(@PathVariable("queueName") String queueName){
        return this.queueService.getMessage(queueName);
    }
}
