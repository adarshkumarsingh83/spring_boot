package com.espark.adarsh.oauthdb.respository;

import com.espark.adarsh.oauthdb.enitities.USER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface USERRepository extends JpaRepository<USER, Integer>, JpaSpecificationExecutor<USER> {

}