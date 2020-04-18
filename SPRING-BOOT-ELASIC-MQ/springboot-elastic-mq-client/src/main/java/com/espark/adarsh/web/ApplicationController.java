package com.espark.adarsh.web;

import com.espark.adarsh.bean.MessageBean;
import com.espark.adarsh.publisher.ApplicationPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationPublisher publisher;

    @PostMapping("/message")
    public String postMessage(@RequestBody MessageBean messageBean) throws Exception {
        log.info("label=postMessage() messageBean={}", messageBean);
        return this.publisher.sendMessage(messageBean);
    }
}
