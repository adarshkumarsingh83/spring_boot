package com.espark.adarsh.persistance;


import com.codahale.metrics.health.HealthCheckRegistry;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.util.Properties;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

@Slf4j
@Component
public class PostgresDatasourceConfigurer implements HealthIndicator {

    HikariDataSource ds;

    SpringDropwizardHealthCheckAdapter postgresPoolHealthCheck;

    @Autowired
    HealthCheckRegistry healthCheckRegistry;

    @Value("${espark.driver.class}")
    String datasourceClass;

    @Value("${espark.datasource.db.name}")
    String databaseName;

    @Value("${espark.datasource.host.name}")
    String databaseHost;

    @Value("${espark.datasource.host.port}")
    String databasePort;

    @Value("${espark.datasource.username}")
    String datasourceUsername;

    @Value("${espark.datasource.password}")
    String datasourcePassword;

    @Value("${espark.datasource.pool.name}")
    String poolName;

    @Value("${espark.datasource.db.schema}")
    String schema;

    @PostConstruct
    public void init() {
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", datasourceClass);
        props.setProperty("dataSource.user", datasourceUsername);
        props.setProperty("dataSource.password", datasourcePassword);
        props.setProperty("dataSource.databaseName", databaseName);
        props.setProperty("dataSource.serverName", databaseHost);
        props.setProperty("dataSource.portNumber", databasePort);
        props.setProperty("dataSource.currentSchema", schema);

        log.info("label=postgresDatasourceProperties user={} host={} port={} database={}", datasourceUsername,
                databaseHost, databasePort, databaseName);

        HikariConfig config = new HikariConfig(props);
        config.setPoolName(poolName);
        config.addHealthCheckProperty("expected99thPercentileMs", "10");
        if (healthCheckRegistry != null) {
            config.setHealthCheckRegistry(healthCheckRegistry);
            postgresPoolHealthCheck = new SpringDropwizardHealthCheckAdapter(healthCheckRegistry,
                    String.format("%s.pool.ConnectivityCheck", poolName));
        }
        ds = new HikariDataSource(config);
    }

    @Bean
    public DataSource getDataSource() {
        return (DataSource) ds;
    }

    @Override
    public Health health() {
        return postgresPoolHealthCheck.health();
    }

    @PreDestroy
    public void destroy() {
        if (ds != null) {
            ds.close();
        }
    }
}
