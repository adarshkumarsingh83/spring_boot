package com.espark.adarsh.service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface InfoService {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String getMsg();
}
