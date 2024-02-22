package com.espark.adarsh.repsotiory;

import com.espark.adarsh.bean.Employee;

import java.util.List;

public interface EmployeeRepository {

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public Employee deleteEmployee(String employeeId);

    public Employee getEmployee(String employeeId);

    public List<Employee> getEmployees();
}
