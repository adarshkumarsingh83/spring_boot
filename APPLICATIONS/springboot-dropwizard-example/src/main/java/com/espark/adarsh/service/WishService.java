package com.espark.adarsh.service;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WishService {

    private Meter wishMeter;
    private Timer wishTimer;


    @Autowired
    public WishService(MetricRegistry metricRegistry) {
        this.wishMeter = metricRegistry.meter("wishMeter");
        this.wishTimer = metricRegistry.timer("wishTimer");
    }

    public Map<String, String> wish(String name) {
        wishMeter.mark();
        Timer.Context timer = wishTimer.time();
        HashMap<String, String> response;

        int hour = LocalDateTime.now().getHour();
        if (hour > 0 && hour < 11) {
            response = new HashMap<String, String>() {
                {
                    {
                        put("message", " Good morning " + name);
                    }
                }
            };
        } else if (hour > 11 && hour < 16) {
            response = new HashMap<String, String>() {
                {
                    {
                        put("message", " Good After noon " + name);
                    }
                }
            };
        } else {
            response = new HashMap<String, String>() {
                {
                    {
                        put("message", " Good Evening " + name);
                    }
                }
            };

        }
        timer.stop();
        return response;
    }
}
