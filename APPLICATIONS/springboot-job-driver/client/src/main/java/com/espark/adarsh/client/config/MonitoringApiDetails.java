package com.espark.adarsh.client.config;

import com.espark.adarsh.client.bean.AbstractApiDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitoringApiDetails extends AbstractApiDetails {
    private Integer monitorWaitTime;
}
