package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;

import java.util.List;

/**
 * Created by akumar6 on 8/24/16.
 */
public interface EmployeeService {

    public boolean saveEmployee(Employee employee);

    public boolean updateEmployee(Employee employee);

    public Employee getEmployee(String employeeId);

    public List<Employee> getEmployees();

    public boolean deleteEmployee(String employeeId);
}
