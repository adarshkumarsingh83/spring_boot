package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.OauthClientToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OauthClientTokenRepository extends JpaRepository<OauthClientToken, String>, JpaSpecificationExecutor<OauthClientToken> {

}