package com.espark.adarsh.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WishService {

    public Map<String, String> wish(String name) {
        int hour = LocalDateTime.now().getHour();
        if (hour > 0 && hour < 11) {
            return new HashMap<String, String>() {
                {
                    {
                        put("message", " Good morning " + name);
                    }
                }
            };
        } else if (hour > 11 && hour < 16) {
            return new HashMap<String, String>() {
                {
                    {
                        put("message", " Good After noon " + name);
                    }
                }
            };
        } else {
            return new HashMap<String, String>() {
                {
                    {
                        put("message", " Good Evening " + name);
                    }
                }
            };
        }
    }
}
