package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "role")
@Entity
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    
}