package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionRoleRepository extends JpaRepository<PermissionRole, Void>, JpaSpecificationExecutor<PermissionRole> {

}