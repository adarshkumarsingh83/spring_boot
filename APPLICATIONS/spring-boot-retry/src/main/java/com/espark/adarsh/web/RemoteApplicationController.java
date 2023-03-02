package com.espark.adarsh.web;

import com.espark.adarsh.service.RemoteApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoteApplicationController {


    @Autowired
    RemoteApplicationService remoteApplicationService;

    @GetMapping("/message/{name}/{type}")
    public Map<String,String> wish(@PathVariable("name") String name,@PathVariable("type") String type) throws InterruptedException {
       return this.remoteApplicationService.wish(name,type);
    }
}
