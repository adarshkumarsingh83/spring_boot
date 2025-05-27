package com.espark.adarsh.repository;

import com.espark.adarsh.record.Employee;
import com.espark.adarsh.exception.EmployeeAlreadyExistsException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

@Getter
@Component
public class EmployeeRepository {

    Logger log = LoggerFactory.getLogger(EmployeeRepository.class);
    static final Map<String, Employee> store = new HashMap<>();
    Random random = new Random();

    public Function<Employee, Employee> saveEmployee = (employee) -> {
        if (store.containsKey(employee.id())) {
            throw new EmployeeAlreadyExistsException("employee exist with id " + employee.id());
        } else {
            //store.put(employee.id(), employee);
            try {
                int sleepTime = random.nextInt(1000);
                log.info("EmployeeRepository::saveEmployee sleepTime {} threadName {} "
                        , sleepTime, Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                 log.error("EmployeeRepository:: error {}",e.getLocalizedMessage());
            }
        }
        return employee;
    };
}
