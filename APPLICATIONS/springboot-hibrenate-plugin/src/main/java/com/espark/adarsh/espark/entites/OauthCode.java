package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Table(name = "oauth_code")
@Data
@Entity
public class OauthCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "authentication")
    private byte[] authentication;

    @Column(name = "code")
    private String code;

    
}