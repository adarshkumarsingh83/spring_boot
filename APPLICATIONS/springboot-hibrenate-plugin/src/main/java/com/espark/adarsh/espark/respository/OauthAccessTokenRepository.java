package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.OauthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken, String>, JpaSpecificationExecutor<OauthAccessToken> {

}