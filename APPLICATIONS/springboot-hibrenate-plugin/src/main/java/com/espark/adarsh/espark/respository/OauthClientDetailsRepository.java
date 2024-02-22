package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String>, JpaSpecificationExecutor<OauthClientDetails> {

}