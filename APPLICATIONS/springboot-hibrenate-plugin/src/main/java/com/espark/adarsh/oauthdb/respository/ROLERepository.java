package com.espark.adarsh.oauthdb.respository;

import com.espark.adarsh.oauthdb.enitities.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ROLERepository extends JpaRepository<ROLE, Integer>, JpaSpecificationExecutor<ROLE> {

}