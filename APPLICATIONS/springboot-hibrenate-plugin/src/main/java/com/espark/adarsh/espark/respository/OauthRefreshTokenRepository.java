package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.OauthRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OauthRefreshTokenRepository extends JpaRepository<OauthRefreshToken, Void>, JpaSpecificationExecutor<OauthRefreshToken> {

}