package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.OauthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OauthCodeRepository extends JpaRepository<OauthCode, Void>, JpaSpecificationExecutor<OauthCode> {

}