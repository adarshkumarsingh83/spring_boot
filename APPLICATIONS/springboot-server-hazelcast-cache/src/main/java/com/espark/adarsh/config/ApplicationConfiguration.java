package com.espark.adarsh.config;

import com.espark.adarsh.bean.Employee;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.logging.LogEvent;
import com.hazelcast.logging.LogListener;
import com.hazelcast.logging.LoggingService;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;

@Configuration
public class ApplicationConfiguration {

     Logger logger= LoggerFactory.getLogger(getClass());
    @Bean
    public Config hazelCastConfig(){
        Config config= new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("employee")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(20))
                                .setProperty("hazelcast.logging.type","slf4j");


        config.getSerializationConfig().getSerializerConfigs().add(
                new SerializerConfig().
                        setTypeClass(Employee.class).
                        setImplementation(new EmployeeSerializer()));

        NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20);
        network.setPortAutoIncrement(true);
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);
        join.getTcpIpConfig()
                .addMember("machine1")
                .addMember("localhost").setEnabled(true);
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        HazelcastInstance instance= Hazelcast.newHazelcastInstance(hazelCastConfig());
        LogListener listener = new LogListener() {
            public void log( LogEvent logEvent ) {
                logger.info(logEvent.toString());
            }
        };
        LoggingService loggingService = instance.getLoggingService();
        loggingService.addLogListener( Level.ALL, listener );
        return instance;
    }

    @Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }
}
