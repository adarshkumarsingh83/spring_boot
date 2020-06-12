package com.espark.adarsh.persistence.repository;



import com.espark.adarsh.persistence.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    public Employee findByEmployeeId(Integer id);
}
