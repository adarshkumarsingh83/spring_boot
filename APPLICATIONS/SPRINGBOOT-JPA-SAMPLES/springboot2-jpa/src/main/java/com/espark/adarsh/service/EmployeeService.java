package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    Employee createEmployee(Employee employee);

    Employee getEmployeeById(Long empId);

    Employee updateEmployee(Long empId, Employee employeeDetails);

    Employee deleteEmployee( Long empId);
}
