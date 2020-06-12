package com.espark.adarsh.respository;

import com.espark.adarsh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(nativeQuery = true, value = "call GET_EMPLOYEE_BY_EMAIL(:employeeEmail)")
     Employee getEmployeeByEmail(@Param("employeeEmail") String employeeEmail);



    @Query(nativeQuery = true, value = "call GET_ALL_EMPLOYEES")
    List<Employee> getAllEmployees();
}