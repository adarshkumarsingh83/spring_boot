package com.espark.adarsh.enitity;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedList;
import java.util.List;

@Data
@NodeEntity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Relationship(type = "WORK_IN")
    private List<Department> departments = new LinkedList<>();


}
