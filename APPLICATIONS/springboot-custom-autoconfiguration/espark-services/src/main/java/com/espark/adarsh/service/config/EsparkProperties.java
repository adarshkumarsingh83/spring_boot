package com.espark.adarsh.service.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "espark.service")
@ConditionalOnProperty(prefix = "espark.service")
public class EsparkProperties {

       String message;
}
