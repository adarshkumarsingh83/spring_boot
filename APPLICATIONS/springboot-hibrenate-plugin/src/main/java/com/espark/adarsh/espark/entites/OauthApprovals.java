package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "oauth_approvals")
@Data
@Entity
public class OauthApprovals implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "clientId")
    private String clientId;

    @Column(name = "expiresAt")
    private Timestamp expiresAt;

    @Column(name = "lastModifiedAt")
    private Timestamp lastModifiedAt;

    @Column(name = "scope")
    private String scope;

    @Column(name = "status")
    private String status;

    @Column(name = "userId")
    private String userId;

    
}