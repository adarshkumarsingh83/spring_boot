package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "oauth_client_token")
@Data
public class OauthClientToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(insertable = false, name = "authentication_id", nullable = false)
    private String authenticationId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "token")
    private byte[] token;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "user_name")
    private String userName;

    
}