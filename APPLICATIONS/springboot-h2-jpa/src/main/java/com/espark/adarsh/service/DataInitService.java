package com.espark.adarsh.service;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.espark.adarsh.util.DatabaseHealthCheck;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Slf4j
@Service
public class DataInitService {


    @Autowired
    DataSource dataSource;

    @Bean
    public MetricRegistry metricRegistry(){
        return new MetricRegistry();
    }

    @PostConstruct
    public void init() {
       /* Resource initSchema = new ClassPathResource("DB.SQL");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);*/
        log.info("label=init() execution successful");

        if(dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).setMetricRegistry(metricRegistry());
        }
        HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();
        healthCheckRegistry.register("db", new DatabaseHealthCheck());
        healthCheckRegistry.runHealthCheck("db");
        log.info("label=setUpHikariWithMetrics() execution successful");
    }
}
