package com.espark.adarsh.listener;

import com.espark.adarsh.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class ApplicationDataListener {
    @StreamListener(Processor.INPUT)
    public void consumeEmployeeDetails(Employee employeeDetails) {
        log.info("Let's process employee details: {}", employeeDetails);
    }
}
