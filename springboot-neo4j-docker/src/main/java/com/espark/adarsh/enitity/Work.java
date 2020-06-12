package com.espark.adarsh.enitity;


import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Data
@RelationshipEntity(type = "WORK_IN")
public class Work {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Employee employee;

    @EndNode
    private Department department;

    private List<String> work = new LinkedList<>();

    public void addWorkName(String name) {
        this.work.add(name);
    }
}
