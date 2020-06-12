package com.espark.adarsh.service;

import com.espark.adarsh.config.VaultDataConfiguration;
import com.espark.adarsh.bean.VaultDataConfigurationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaultConfigurationService {
    @Autowired
    VaultDataConfiguration vaultDataConfiguration;

    public VaultDataConfigurationBean getVaultDataConfiguration(){
        return new VaultDataConfigurationBean(this.vaultDataConfiguration);
    }
}
