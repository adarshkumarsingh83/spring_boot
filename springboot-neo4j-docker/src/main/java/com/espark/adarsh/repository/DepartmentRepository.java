package com.espark.adarsh.repository;


import com.espark.adarsh.enitity.Department;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends Neo4jRepository<Department, Long> {

    List<Department> findByNameLike(@Param("name") String name);


    @Query("MATCH (d:Department)<-[w:WORKS]-(e:Employee) RETURN d,w,e LIMIT $limit")
    List<Department> graph(@Param("limit") int limit);
}
