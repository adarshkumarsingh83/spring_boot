package com.espark.adarsh.persistence.respository;

import com.espark.adarsh.persistence.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee,Long>{
}
