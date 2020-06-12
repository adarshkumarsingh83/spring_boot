package com.espark.adarsh.repository;


import com.espark.adarsh.enitity.Department;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DepartmentRepository extends Neo4jRepository<Department, Long> {

}
