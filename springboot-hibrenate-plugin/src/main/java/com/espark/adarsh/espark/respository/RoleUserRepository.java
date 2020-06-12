package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleUserRepository extends JpaRepository<RoleUser, Void>, JpaSpecificationExecutor<RoleUser> {

}