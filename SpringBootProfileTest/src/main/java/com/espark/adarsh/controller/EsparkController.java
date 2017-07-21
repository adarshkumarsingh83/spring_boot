package com.espark.adarsh.controller;

import com.espark.adarsh.service.EsparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/application")
public class EsparkController {

    @Qualifier("esparkService")
    @Autowired
    private EsparkService esparkService;


    @RequestMapping(value = "/welcome-html"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    String welcomeMessage() {
        return "<h1>" +
                "<br/><br/>" +
                "<center>" + this.esparkService.getWishMessage() + "</center>"
                + "</h1>";
    }

    @RequestMapping(value = "/welcome-json"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    HashMap welcomeMessageJson() {
        final String wishMessage = this.esparkService.getWishMessage();
        return new HashMap<String, String>() {
            {
                put("message", wishMessage);
            }
        };
    }

}
