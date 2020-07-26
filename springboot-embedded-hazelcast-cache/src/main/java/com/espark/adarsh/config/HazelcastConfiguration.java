package com.espark.adarsh.config;

import com.hazelcast.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HazelcastConfiguration {

    @Autowired
    AppConfigProp appConfigProp;

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config()
                .setInstanceName(appConfigProp.getInstanceName())
                .addMapConfig(
                        new MapConfig()
                                .setName(appConfigProp.getCacheName())
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(20));

        config.getGroupConfig().setName(appConfigProp.getUserName());
        config.getGroupConfig().setPassword(appConfigProp.getUserPwd());
        config.getManagementCenterConfig().setEnabled(true);
        config.getManagementCenterConfig().setUrl(appConfigProp.getManagementUrl());

        ReplicatedMapConfig replicatedMapConfig = new ReplicatedMapConfig();
        replicatedMapConfig.setName(appConfigProp.getRepCacheName());
        replicatedMapConfig.setAsyncFillup(true);
        config.addReplicatedMapConfig(replicatedMapConfig);

        config.setProperty("hazelcast.local.publicAddress", appConfigProp.getPublicAddress());
        return config;
    }

   /*
    @Bean
    // hazelcast 4.x configuration
    public Config hazelCastConfig() {
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName(appConfigProp.getCacheName());

        Config config = new Config()
                .setInstanceName(appConfigProp.getInstanceName())
                .addMapConfig(
                        new MapConfig()
                                .setName(appConfigProp.getCacheName())
                                .setTimeToLiveSeconds(20));


        ManagementCenterConfig managementCenterConfig = config.getManagementCenterConfig();
        Set set = new HashSet<>();
        set.add(appConfigProp.getManagementUrl());
        managementCenterConfig.setTrustedInterfaces(set);
        config.setManagementCenterConfig(managementCenterConfig);

        ReplicatedMapConfig replicatedMapConfig = new ReplicatedMapConfig();
        replicatedMapConfig.setName(appConfigProp.getRepCacheName());
        replicatedMapConfig.setAsyncFillup(true);
        config.addReplicatedMapConfig(replicatedMapConfig);
        //config.setProperty("hazelcast.local.publicAddress", appConfigProp.getPublicAddress());
        return config;
    }*/

}