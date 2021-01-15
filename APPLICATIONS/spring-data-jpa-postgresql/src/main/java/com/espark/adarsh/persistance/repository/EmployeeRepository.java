package com.espark.adarsh.persistance.repository;

import com.espark.adarsh.persistance.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);

}
