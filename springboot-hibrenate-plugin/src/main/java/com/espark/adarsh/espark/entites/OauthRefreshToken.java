package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "authentication")
    private byte[] authentication;

    @Column(name = "token")
    private byte[] token;

    @Column(name = "token_id")
    private String tokenId;

    
}