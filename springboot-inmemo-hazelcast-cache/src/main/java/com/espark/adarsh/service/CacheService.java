package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface CacheService {
    @CacheEvict(allEntries = true)
    void clearCache();

    @Cacheable(condition = "#employee.getId('100')")
    Employee getEmployee();

    void setEmployee(Employee employee);
}
