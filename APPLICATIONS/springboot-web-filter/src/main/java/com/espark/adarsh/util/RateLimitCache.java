package com.espark.adarsh.util;

import com.espark.adarsh.bean.ApiConfig;
import com.espark.adarsh.bean.RateLimitConfig;
import com.espark.adarsh.predicate.EsparkPredicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Component
public class RateLimitCache {

    public static final Map<String, Integer> requestCounterStore = new HashMap<>();

    @Autowired
    RateLimitConfig config;

    @PostConstruct
    public void init() {
        if (config.isEnable()) {
            this.initCache.accept(config.getApiConfig());
        }
    }

    public EsparkPredicate<ApiConfig, String, String> checkRequestLimit = ((apiConfig, url, httpMethod) -> {
        boolean result = true;

        if (requestCounterStore.containsKey(httpMethod + ":" + url)) {
            Integer currentCount = requestCounterStore.get(httpMethod + ":" + url);
            if (currentCount < apiConfig.getThreshold()) {
                requestCounterStore.put(httpMethod + ":" + url, currentCount + 1);
            } else {
                result = false;
            }
        }
        return result;
    });


    public Consumer<List<ApiConfig>> initCache = (apiConfigs -> {
        apiConfigs.stream()
                .filter(ApiConfig::isEnable)
                .forEach(config -> {
                    requestCounterStore.put(config.getHttpMethod() + ":" + config.getUrl(), 0);
                });
    });


    @Scheduled(cron = "${espark.job.cron}")
    public void restCatch() {
        log.info("restCatch executed hour {} minutes {}", LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());
        if (config.isEnable()) {
            this.initCache.accept(config.getApiConfig());
        }
    }

}
