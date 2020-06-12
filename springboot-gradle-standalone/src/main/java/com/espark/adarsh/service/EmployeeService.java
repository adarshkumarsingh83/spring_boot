package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public Employee deleteEmployee(String employeeId);

    public Employee getEmployee(String employeeId);

    public List<Employee> getEmployees();

}
