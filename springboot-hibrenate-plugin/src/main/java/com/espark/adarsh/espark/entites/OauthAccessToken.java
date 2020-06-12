package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

[B;

@Data
@Entity
@Table(name = "oauth_access_token")
public class OauthAccessToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "authentication")
    private byte[] authentication;

    @Id
    @Column(insertable = false, name = "authentication_id", nullable = false)
    private String authenticationId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "token")
    private byte[] token;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "user_name")
    private String userName;

    
}