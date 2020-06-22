package com.espark.adarsh.repository;


import com.espark.adarsh.enitity.Department;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DepartmentRepository extends Neo4jRepository<Department, Long> {


    @Query("MATCH (d:Department) WHERE d.name =~ ('(?i).*'+{$name}+'.*') RETURN d")
    List<Department> findByNameContaining(@Param("name") String name);

    @Query("MATCH (d:Department)-[:WORKS]-(e:Employee) RETURN d.name as department, collect(e.name) as employee LIMIT {$limit}")
    List<Map<String,Object>> graph(@Param("limit") int limit);

}
