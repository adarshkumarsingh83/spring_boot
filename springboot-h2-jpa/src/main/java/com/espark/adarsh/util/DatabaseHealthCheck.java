package com.espark.adarsh.util;

import com.codahale.metrics.health.HealthCheck;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@Service
public class DatabaseHealthCheck extends HealthCheck {

    @Autowired
    DataSource dataSource;

    protected Result check() throws Exception {
        // this has to linked with acchuater
        log.info("label=dbCheck() execution ");
        if(!((HikariDataSource) dataSource).isClosed()) {
            return Result.healthy();
        }else{
            return Result.unhealthy("CONNECTION POOL IS DOWN");
        }
    }

}