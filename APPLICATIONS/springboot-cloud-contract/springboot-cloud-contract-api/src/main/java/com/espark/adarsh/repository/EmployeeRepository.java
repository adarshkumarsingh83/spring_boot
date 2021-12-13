package com.espark.adarsh.repository;

import com.espark.adarsh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
}
