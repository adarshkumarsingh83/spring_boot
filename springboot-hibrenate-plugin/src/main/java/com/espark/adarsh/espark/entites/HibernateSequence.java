package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "hibernate_sequence")
@Data
@Entity
public class HibernateSequence implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "next_val")
    private Long nextVal;

    
}