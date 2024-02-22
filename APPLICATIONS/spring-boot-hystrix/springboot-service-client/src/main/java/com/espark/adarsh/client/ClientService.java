package com.espark.adarsh.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        name = "springboot-service"
        ,fallback = ClientFallback.class
)
public interface ClientService {

    @GetMapping("/message/{username}")
    public String greeting(@PathVariable("username") String username);
}
