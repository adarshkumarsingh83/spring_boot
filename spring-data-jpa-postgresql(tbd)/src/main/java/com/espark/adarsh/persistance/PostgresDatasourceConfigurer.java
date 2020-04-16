package com.espark.adarsh.persistance;


import com.codahale.metrics.health.HealthCheckRegistry;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${authoring.datasource.class:org.postgresql.ds.PGSimpleDataSource}")
    String datasourceClass;

    @Value("${authoring.database.name}")
    String databaseName;

    @Value("${authoring.database.host}")
    String databaseHost;

    @Value("${authoring.database.port}")
    String databasePort;

    @Value("${authoring.database.username}")
    String datasourceUsername;

    @Value("${authoring.database.password}")
    String datasourcePassword;

    @Value("${authoring.datasource.poolName}")
    String poolName;

    @Value("${documentMK.nodeCachePerc:1}")
    int nodeCachePerc;

    @Value("${documentMK.prevDocCachePerc:1}")
    int prevDocCachePerc;

    @Value("${documentMK.childrenCachePerc:1}")
    int childrenCachePerc;

    @Value("${documentMK.diffCachePerc:1}")
    int diffCachePerc;

    @Value("${activeCluster:true}")
    boolean activeCluster;

    @PostConstruct
    public void init() {
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", datasourceClass);
        props.setProperty("dataSource.user", datasourceUsername);
        props.setProperty("dataSource.password", datasourcePassword);
        props.setProperty("dataSource.databaseName", databaseName);
        props.setProperty("dataSource.serverName", databaseHost);
        props.setProperty("dataSource.portNumber", databasePort);
        props.setProperty("dataSource.currentSchema", "public");

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
