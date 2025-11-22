package com.espark.adarsh.application.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MESSAGES")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    private LocalDateTime createdAt;

}
