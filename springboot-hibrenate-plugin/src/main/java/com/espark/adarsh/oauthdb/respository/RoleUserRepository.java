package com.espark.adarsh.oauthdb.respository;

import com.espark.adarsh.oauthdb.enitities.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleUserRepository extends JpaRepository<RoleUser, Void>, JpaSpecificationExecutor<RoleUser> {

}