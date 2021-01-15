package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.OauthApprovals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OauthApprovalsRepository extends JpaRepository<OauthApprovals, Void>, JpaSpecificationExecutor<OauthApprovals> {

}