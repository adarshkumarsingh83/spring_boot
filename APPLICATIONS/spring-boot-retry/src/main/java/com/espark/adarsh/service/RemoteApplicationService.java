package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class RemoteApplicationService {

    public Map<String, String> wish(String name, String type) throws InterruptedException {
        if (type.equals("DELAY")) {
            log.info("delay executed ");
            Thread.currentThread().sleep(10000);
            return new HashMap<String, String>() {
                {
                    put("msg", "welcome to espark ");
                    put("name", name);
                }
            };
        }
        if (type.equals("ERROR")) {
            log.info("error executed ");
            throw new RuntimeException("Server not willing to respond ...");
        } else {
            log.info("normal executed ");
            return new HashMap<String, String>() {
                {
                    put("msg", "welcome to espark ");
                    put("name", name);
                }
            };
        }
    }
}
