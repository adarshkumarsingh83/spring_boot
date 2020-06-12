package com.espark.adarsh.web;

import com.espark.adarsh.bean.VaultDataConfigurationBean;
import com.espark.adarsh.service.VaultConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    VaultConfigurationService vaultConfigurationService;

    @GetMapping("/configuration")
    public VaultDataConfigurationBean getConfiguration() {
        return this.vaultConfigurationService.getVaultDataConfiguration();
    }

}
