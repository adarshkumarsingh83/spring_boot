package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private static final Map<String, Employee> employeeDataStore =
            new HashMap<String, Employee>() {
                {
                    put("100", new Employee("100", "adarsh kumar", "adarsh@kumar"));
                    put("101", new Employee("101", "radha singh", "radha@singh"));
                    put("102", new Employee("102", "amit kumar", "amit@kumar"));
                }
            };

    @Override

    public boolean saveEmployee(Employee employee) {
        employeeDataStore.put(employee.getEmployeeId(), employee);
        return true;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        employeeDataStore.put(employee.getEmployeeId(), employee);
        return true;
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return employeeDataStore.get(employeeId);
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<Employee>(employeeDataStore.values());
    }

    @Override
    public boolean deleteEmployee(String employeeId) {
        employeeDataStore.remove(employeeId);
        return true;
    }
}
