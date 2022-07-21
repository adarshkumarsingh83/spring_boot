package com.espark.adarsh.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeUtil {

    private DateTimeFormatter dtfDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public String getTime() {
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, ZoneId.systemDefault());
        return dtfDateTime.format(zonedDateTime);
    }
}
