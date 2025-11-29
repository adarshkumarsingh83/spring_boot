package com.espark.adarsh.service;

import com.espark.adarsh.entities.Employee;
import com.espark.adarsh.exception.EmployeeNotFound;
import com.espark.adarsh.repository.EmployeeRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Getter
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private final Function<Employee, Employee> saveEmployeeFunction = (employee) -> {
        employee = this.employeeRepository.save(employee);
        log.info("Employee Saved Successfully :: {}", employee);
        return employee;
    };

    private final Function<Long, Employee> employeeByIdFunction = (employeeId) -> {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> {
            log.error("Employee Not Found For Get Operation Id :: {}", employeeId);
            return new EmployeeNotFound("Employee Not Found For Id :: " + employeeId);
        });
        log.info("Employee Retrieved Successfully :: {}", employee);
        return employee;
    };

    private final Function<Long, Employee> deleteEmployeeByIdFunction = (employeeId) -> {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> {
            log.error("Employee Not Found For Delete Operation Id :: {}", employeeId);
            return new EmployeeNotFound("Employee Not Found For Id :: " + employeeId);
        });
        this.employeeRepository.deleteById(employeeId);
        log.info("Employee Deleted Successfully :: {}", employee);
        return employee;
    };

    private final Supplier<Iterable<Employee>> employeesFunction = () -> {
        Iterable<Employee> employees = this.employeeRepository.findAll();
        log.info("Employees Retrieved Successfully :: {}", employees);
        return employees;
    };

    private final BiFunction<Long, Employee, Employee> updateEmployeeFunction = (employeeId, employee) -> {
        this.employeeRepository.findById(employeeId).orElseThrow(() -> {
            log.error("Employee Not Found For Update Operation Id :: {}", employeeId);
            return new EmployeeNotFound("Employee Not Found For Id :: " + employeeId);
        });
        employee = this.employeeRepository.save(employee);
        log.info("Employee Updated Successfully :: {}", employee);
        return employee;
    };

}
