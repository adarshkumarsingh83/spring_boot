package com.espark.adarsh.espark.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "oauth_client_details")
@Entity
public class OauthClientDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "additional_information")
    private String additionalInformation;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "autoapprove")
    private String autoapprove;

    @Id
    @Column(insertable = false, name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "resource_ids")
    private String resourceIds;

    @Column(name = "scope")
    private String scope;

    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;

    
}