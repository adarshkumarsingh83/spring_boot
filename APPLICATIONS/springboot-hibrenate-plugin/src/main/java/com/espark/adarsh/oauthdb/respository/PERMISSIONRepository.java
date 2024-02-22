package com.espark.adarsh.oauthdb.respository;

import com.espark.adarsh.oauthdb.enitities.PERMISSION;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PERMISSIONRepository extends JpaRepository<PERMISSION, Integer>, JpaSpecificationExecutor<PERMISSION> {

}