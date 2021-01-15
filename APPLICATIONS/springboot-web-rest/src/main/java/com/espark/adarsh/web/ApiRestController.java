package com.espark.adarsh.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiRestController {
    
    @GetMapping("/message")
    public Map<String, String> getMessage() {
        Map<String, String> message = new HashMap<String, String>();
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
            message.put("message", " WELCOME FORM ESPARK ");
            message.put("ip", " Your current IP address : " + ip);
            message.put("host", " Your current Hostname : " + hostname);
        } catch (UnknownHostException e) {
            log.error("label=api-rest-controller {}", e.getMessage());
        }
        log.info("label=api-rest-controller getMessage() ");
        return message;
    }
}
