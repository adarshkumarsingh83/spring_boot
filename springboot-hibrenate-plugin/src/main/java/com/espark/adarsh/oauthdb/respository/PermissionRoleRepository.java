package com.espark.adarsh.oauthdb.respository;

import com.espark.adarsh.oauthdb.enitities.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionRoleRepository extends JpaRepository<PermissionRole, Void>, JpaSpecificationExecutor<PermissionRole> {

}