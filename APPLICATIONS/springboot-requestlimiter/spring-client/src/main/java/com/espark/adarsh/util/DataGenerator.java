package com.espark.adarsh.util;

import com.espark.adarsh.record.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DataGenerator {

    static final Logger log = LoggerFactory.getLogger(DataGenerator.class);

    public BiFunction<String,Integer, List<Employee>> generateEmployeeData = (batchId,size) -> {
        IntStream numberStream = IntStream.rangeClosed(1, size);
        log.info("DataGenerator::generateEmployeeData batchId {} ", batchId);
        return numberStream.sequential()
                .mapToObj(i -> {
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        log.error("Exception Generated while Generating data {}", e.getMessage());
                    }
                    return new Employee(batchId+ "-" + i + "-" + System.currentTimeMillis(), "name" + 1, "name" + 1 + "@email.com");
                })
                .collect(Collectors.toList());
    };
}
