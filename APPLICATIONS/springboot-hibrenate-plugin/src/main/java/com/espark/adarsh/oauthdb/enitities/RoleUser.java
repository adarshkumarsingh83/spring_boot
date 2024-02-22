package com.espark.adarsh.oauthdb.enitities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "role_user")
@Entity
public class RoleUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "USER_ID")
    private Integer userId;

    
}