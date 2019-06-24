package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    Map<String,Employee> employeeStore=new HashMap<>();

     @Autowired
     private MessageService messageService;

    public Employee saveEmployee(Employee employee){

        if(employee==null){
           throw new ValidationException("null.emp",messageService.get("null.emp"));
        }

        if(StringUtils.isEmpty(employee.getId())){
            throw new ValidationException("id.empty",messageService.get("id.empty"));
        }

        if(StringUtils.isEmpty(employee.getName())){
            throw new ValidationException("name.empty",messageService.get("name.empty"));
        }
        employeeStore.put(employee.getId(),employee);
        return employee;
    }
}
