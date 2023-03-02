package com.espark.adarsh.producer;

import com.espark.adarsh.entities.Employee;
import com.espark.adarsh.entities.EmployeeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Service
public class ApplicationDataProducer {

    @Autowired
    private Processor processor;

    public String publishData(Employee employee) {
        EmployeeKey employeeKey = new EmployeeKey();
        employeeKey.setId(employee.getId());
        employeeKey.setFirstName(employee.getFirstName());
        Message<Employee> message = MessageBuilder.withPayload(employee)
                .setHeader(KafkaHeaders.RECORD_METADATA, employeeKey)
                .build();
        boolean result = processor.output()
                .send(message);
        return result ? "data published successfully " : " data not published ";
    }

}
