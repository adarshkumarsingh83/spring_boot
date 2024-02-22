package com.espark.adarsh.oauthdb.enitities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "USER")
public class USER implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ACCOUNT_EXPIRED", nullable = false)
    private Boolean accountExpired;

    @Column(name = "ACCOUNT_LOCKED", nullable = false)
    private Boolean accountLocked;

    @Column(name = "CREDENTIALS_EXPIRED", nullable = false)
    private Boolean credentialsExpired;

    @Column(name = "EMAIL", nullable = false)
    private String EMAIL;

    @Column(name = "ENABLED", nullable = false)
    private Boolean ENABLED;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false, nullable = false)
    private Integer ID;

    @Column(name = "PASSWORD", nullable = false)
    private String PASSWORD;

    @Column(name = "USERNAME", nullable = false)
    private String USERNAME;

    
}