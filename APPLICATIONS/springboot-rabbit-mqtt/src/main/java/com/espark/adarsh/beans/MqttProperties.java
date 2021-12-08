package com.espark.adarsh.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */

@Data
@Primary
@Validated
@Configuration
@ConfigurationProperties(prefix = "mqtt-properties")
public class MqttProperties {

    @NotBlank(message = "mqtt host url is mandatory properties in configuration ")
    @NotNull(message = "mqtt host url is mandatory properties in configuration ")
    private String host;
    @NotBlank(message = "clientId is mandatory properties in configuration ")
    @NotNull(message = "clientId is mandatory properties in configuration ")
    private String clientId;
    @NotBlank(message = "mqtt username is mandatory properties in configuration ")
    @NotNull(message = "mqtt username is mandatory properties in configuration ")
    private String username;
    @NotBlank(message = "mqtt password is mandatory properties in configuration ")
    @NotNull(message = "mqtt password is mandatory properties in configuration ")
    private String password;
    @NotBlank(message = "mqtt jmri subscribe topic is mandatory properties in configuration ")
    @NotNull(message = "mqtt jmri subscribe topic is mandatory properties in configuration ")
    private String topicSub;
    @NotBlank(message = "mqtt jmri publish topic is mandatory properties in configuration ")
    @NotNull(message = "mqtt jmri publish topic is mandatory properties in configuration ")
    private String topicPublish;

    private String errorTopic;
    private Boolean cleanSession;
    private Integer connectionTimeout;
    private Integer keepAliveInterval;
    private Boolean automaticReconnect;

}
