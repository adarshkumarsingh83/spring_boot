package com.espark.adarsh.producer.service;

import com.espark.adarsh.producer.bean.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

    private static List<Employee> empList = new ArrayList<>();

    static {
        empList.add(new Employee(1, "Adarsh","kumar", 1000.0,"M"));
        empList.add(new Employee(2, "Radha","Singh", 2000.0,"F"));
    }

    public Optional<Employee> findById(Integer id){
        return empList.stream().filter(emp -> emp.id.equals(id)).findFirst();
    }
}
