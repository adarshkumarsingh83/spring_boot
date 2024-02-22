package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user")
@Entity
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "accountNonExpired", nullable = false)
    private Integer accountNonExpired;

    @Column(name = "accountNonLocked", nullable = false)
    private Integer accountNonLocked;

    @Column(name = "credentialsNonExpired", nullable = false)
    private Integer credentialsNonExpired;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private Integer enabled;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    
}