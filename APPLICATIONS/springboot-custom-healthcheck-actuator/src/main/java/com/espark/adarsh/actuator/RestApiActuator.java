package com.espark.adarsh.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RestControllerEndpoint(id = "rest-api")
public class RestApiActuator {

    @GetMapping
    public Map<String, String> getRestApiActuatorHealth() {
        log.info("RestApiActuator::getRestApiActuatorHealth()");
        return  new HashMap<>(){
            {
                put("server-api","server api is up and running");
                put("request-type","server api get request");
                put("server.date", LocalDate.now().toString());
                put("server.time", LocalTime.now().toString());
            }
        };
    }

    @PostMapping
    public Map<String, String> postRequest(@RequestBody String request) {
        log.info("RestApiActuator::postRequest()");
        return  new HashMap<>(){
            {
                put("server-api","server api is up and running");
                put("request-type","server api post request");
                put("server.date", LocalDate.now().toString());
                put("server.time", LocalTime.now().toString());
            }
        };
    }

}
