package com.espark.adarsh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping(value = "/msg/{name}",headers = "X-API-VERSION-ESPARK=V1")
    public String getMessageV1(@PathVariable("name")String name){
        return "welcome from v1 services "+name;
    }

    @GetMapping(value = "/msg/{name}",headers = "X-API-VERSION-ESPARK=V2")
    public String getMessageV2(@PathVariable("name")String name){
        return "welcome from v2 services "+name;
    }
}
