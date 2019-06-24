package com.espark.adarsh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class InstanceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${zuul.server.url}")
    private String zuulServerUrl;

    @GetMapping(value = "/service-discovery")
    public @ResponseBody List<String> list() {
        List<String> instanceList = this.discoveryClient.getServices();
        List<String> serviceEndpointUrlsList = new LinkedList<>();
        instanceList.stream().forEach(instanceName->{
            serviceEndpointUrlsList.add(zuulServerUrl+instanceName+"/service-instances/" + instanceName);
        });
        return serviceEndpointUrlsList;
    }
}
