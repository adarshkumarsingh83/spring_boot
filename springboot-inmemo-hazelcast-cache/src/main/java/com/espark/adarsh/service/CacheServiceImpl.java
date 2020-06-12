package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "employee")
public class CacheServiceImpl implements CacheService{

    private static Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);

    @CacheEvict(allEntries = true)
    @Override
    public void clearCache(){}

    @Cacheable("employee")
    @Override
    public Employee getEmployee() {
        return new Employee();
    }


    @CachePut(value = "employee", key = "#employee + 1") // (2)
    public void setEmployee(Employee employee){
        log.info("employee saved ",employee);
    }
}
