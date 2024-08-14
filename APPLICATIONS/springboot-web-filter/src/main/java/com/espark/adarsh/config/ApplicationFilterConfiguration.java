package com.espark.adarsh.config;

import com.espark.adarsh.bean.ApiConfig;
import com.espark.adarsh.bean.RateLimitConfig;
import com.espark.adarsh.filter.ApplicationFilter;
import com.espark.adarsh.util.MessageStore;
import com.espark.adarsh.util.RateLimitCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.AbstractFilterRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Configuration
public class ApplicationFilterConfiguration {

    @Autowired
    RateLimitConfig rateLimitConfig;


    @Autowired
    RateLimitCache rateLimitCache;

    @Autowired
    MessageStore messageStore;


    @Bean
    public FilterRegistrationBean<ApplicationFilter> filter() {
        FilterRegistrationBean<ApplicationFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        if (rateLimitConfig.isEnable()) {
            filterRegistrationBean.setFilter(new ApplicationFilter(rateLimitCache, rateLimitConfig, messageStore));
            String[] url = this.getApiUrl.apply(rateLimitConfig.getApiConfig());
            if (url != null) {
                filterRegistrationBean.addUrlPatterns(url);
            }
        }
        return filterRegistrationBean;
    }


    public Function<List<ApiConfig>, String[]> getApiUrl = (apiConfigs -> {
        if (apiConfigs != null && !apiConfigs.isEmpty()) {
            return apiConfigs.stream()
                    .filter(ApiConfig::isEnable)
                    .map(ApiConfig::getUrl)
                    .toList()
                    .toArray(String[]::new);
        }
        return null;
    });
}
