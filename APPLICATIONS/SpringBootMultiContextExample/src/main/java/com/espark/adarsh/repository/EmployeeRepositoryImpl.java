package com.espark.adarsh.repository;

import com.espark.adarsh.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository(value = "employeeRepository")
public class EmployeeRepositoryImpl implements EmployeeRepository {

    Map<String,EmployeeEntity> employeeStore=new HashMap<String,EmployeeEntity>(){
        {
            put("100",new EmployeeEntity("100","test"));
        }
    };

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
         employeeStore.put(employee.getEmpId(),employee);
         return employee;
    }

    @Override
    public EmployeeEntity getEmployee(String empId) {
        return employeeStore.get(empId);
    }
}
