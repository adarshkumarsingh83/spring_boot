package com.espark.adarsh.persistance;


import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import org.springframework.boot.actuate.health.Health;

public class SpringDropwizardHealthCheckAdapter {


    HealthCheckRegistry dropWizardHealthCheckRegistry;
    String dropWizardHealthCheckName;

    public Health health() {
        if (dropWizardHealthCheckRegistry != null && dropWizardHealthCheckName != null) {
            HealthCheck.Result result = dropWizardHealthCheckRegistry.runHealthCheck(dropWizardHealthCheckName);
            if (result.isHealthy()) {
                return Health.up().withDetail("message", result.getMessage() != null ? result.getMessage() : "null")
                        .build();
            }
            return Health.down(result.getError() instanceof Exception ? (Exception) result.getError() :
                    new Exception(result.getError()))
                    .withDetail("message", result.getMessage() != null ? result.getMessage() : "null").build();
        }
        return null;
    }


    public SpringDropwizardHealthCheckAdapter(
            HealthCheckRegistry dropWizardHealthCheckRegistry, String dropWizardHealthCheckName) {
        this.dropWizardHealthCheckRegistry = dropWizardHealthCheckRegistry;
        this.dropWizardHealthCheckName = dropWizardHealthCheckName;
    }
}

