package com.espark.adarsh.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IntegrationServiceHealthIndicator implements HealthIndicator {

    private final String message_key = "Integration-Service";

    @Override
    public Health health() {
        log.info("IntegrationServiceHealthIndicator::health()");
        if (!isRunningIntegrationService()) {
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up().withDetail(message_key, "Available").build();
    }

    private Boolean isRunningIntegrationService() {
        log.info("IntegrationServiceHealthIndicator::isRunningIntegrationService()");
        Boolean isRunning = false;
        // temp logic
        if (System.nanoTime() % 2 == 0L) {
            isRunning = true;
        }
        return isRunning;
    }
}
