package com.espark.adarsh.repository;

import com.espark.adarsh.enitity.Employee;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EmployeeRepository  extends Neo4jRepository<Employee, Long> {


}
