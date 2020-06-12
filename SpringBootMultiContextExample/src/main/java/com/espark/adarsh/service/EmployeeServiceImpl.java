package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.entity.EmployeeEntity;
import com.espark.adarsh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeBean saveEmployee(EmployeeBean employee) {
        EmployeeEntity employeeEntity= this.employeeRepository.saveEmployee(new EmployeeEntity(employee));
        return  new EmployeeBean(employeeEntity);
    }

    @Override
    public EmployeeBean getEmployee(String empId) {
        EmployeeEntity employeeEntity= this.employeeRepository.getEmployee(empId);
        return  new EmployeeBean(employeeEntity);
    }
}
