package com.espark.adarsh.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Endpoint(id = "server-operation")
public class ServerOperationActuator {

    @ReadOperation
    public Map<String, String> readOperation() {
        log.info("ServerOperationActuator::readOperation()");
        return new HashMap<>() {
            {
                put("server-operation-api", "server api is up and running");
                put("operation-type", "server-api-read-operation");
                put("server.date", LocalDate.now().toString());
                put("server.time", LocalTime.now().toString());
            }
        };
    }

    @WriteOperation
    public Map<String, String> writeOperation() {
        log.info("ServerOperationActuator::writeOperation()");
        return new HashMap<>() {
            {
                put("server-operation-api", "server api is up and running");
                put("operation-type", "server-api-write-operation");
                put("server.date", LocalDate.now().toString());
                put("server.time", LocalTime.now().toString());
            }
        };
    }

    @DeleteOperation
    public Map<String, String> deleteOperation() {
        log.info("ServerOperationActuator::deleteOperation()");
        return new HashMap<>() {
            {
                put("server-operation-api", "server api is up and running");
                put("operation-type", "server-api-delete-operation");
                put("server.date", LocalDate.now().toString());
                put("server.time", LocalTime.now().toString());
            }
        };
    }
}