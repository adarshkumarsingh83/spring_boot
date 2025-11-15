package com.espark.adarsh.client.config;

import com.espark.adarsh.client.bean.AbstractApiDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "espark.job.status")
public class StatusJobConfigs {

    private Map<String, AbstractApiDetails> apiConfigs = new HashMap();

}
