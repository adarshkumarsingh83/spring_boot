package com.espark.adarsh.oauthdb.enitities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "permission_role")
@Data
@Entity
public class PermissionRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "PERMISSION_ID")
    private Integer permissionId;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    
}