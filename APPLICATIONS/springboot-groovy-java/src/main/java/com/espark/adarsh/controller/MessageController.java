package com.espark.adarsh.controller;

import com.espark.adarsh.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Object getMessage() {
        return this.messageService.getMessage();
    }
}
