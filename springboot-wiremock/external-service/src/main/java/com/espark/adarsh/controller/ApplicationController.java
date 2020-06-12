package com.espark.adarsh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {


    @RequestMapping(value = "/messages",method = RequestMethod.GET)
    public Map<String,String> getMssage(){
        return new HashMap<String,String>(){
            {
                put("name","adarsh kumar");
                put("msg","welcome");
            }
        };
    }

}
