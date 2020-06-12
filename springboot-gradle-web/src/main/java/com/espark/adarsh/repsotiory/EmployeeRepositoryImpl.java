package com.espark.adarsh.repsotiory;

import com.espark.adarsh.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.*;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Map<String, Employee> employeeStore = new HashMap<String, Employee>(){
        {
            put("100",new Employee("100","adarsh kumar"));
        }
    };

    @Override
    public Employee saveEmployee(Employee employee) {
        this.employeeStore.put(employee.getEmpId(), employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        this.employeeStore.put(employee.getEmpId(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String employeeId) {
        return this.employeeStore.remove(employeeId);
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return this.employeeStore.get(employeeId);
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employeeStore.values());
    }
}
