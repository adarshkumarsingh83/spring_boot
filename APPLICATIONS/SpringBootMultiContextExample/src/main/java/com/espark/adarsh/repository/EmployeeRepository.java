package com.espark.adarsh.repository;

import com.espark.adarsh.entity.EmployeeEntity;

public interface EmployeeRepository {

    public EmployeeEntity saveEmployee(EmployeeEntity employee);

    public EmployeeEntity getEmployee(String empId);
}
