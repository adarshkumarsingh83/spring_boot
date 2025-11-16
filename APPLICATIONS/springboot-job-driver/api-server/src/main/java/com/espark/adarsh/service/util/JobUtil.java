package com.espark.adarsh.service.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Getter
@Slf4j
@Component
public class JobUtil {

    final Function<String, LocalDateTime> getJobExitTime = (jobTime) -> {
        Long time = Long.parseLong(jobTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.plusMinutes(time);
    };
}
