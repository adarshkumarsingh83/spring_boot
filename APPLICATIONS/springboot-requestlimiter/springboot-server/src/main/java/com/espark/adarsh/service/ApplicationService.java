package com.espark.adarsh.service;

import com.espark.adarsh.record.Employee;
import com.espark.adarsh.bean.RequestBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ApplicationService {

    Logger log = LoggerFactory.getLogger(ApplicationService.class);
    EmployeeRepository employeeRepository;

    public ApplicationService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    public Function<RequestBean<Employee>, ResponseBean> saveEmployee = (request) -> {
        log.info("ApplicationService::saveEmployee {}", request.getData());
        Employee newEmployee = this.employeeRepository.saveEmployee.apply(request.getData());
        ResponseBean<Employee> employeeCreated = new ResponseBean();
        employeeCreated.setDate(newEmployee);
        employeeCreated.setMessage("employee Created ");
        return employeeCreated;
    };
}
