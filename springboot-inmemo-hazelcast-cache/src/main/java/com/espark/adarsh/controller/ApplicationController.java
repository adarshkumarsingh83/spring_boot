package com.espark.adarsh.controller;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.service.CacheService;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class ApplicationController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HazelcastInstance instance;

    @Autowired
    private CacheService cacheService;



    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public Object getCachedObject(){
//        long start = System.nanoTime();
//        Employee employee =  this.cacheService.getData();
//        long end = System.nanoTime();
//        long time = TimeUnit.NANOSECONDS.toMillis(end - start);
//        logger.info(time+" "+employee);
//        return employee;
        return null;
    }

    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public void setCachedObject(@RequestBody Employee employee){
       // this.cacheService.setData(employee);
    }

    @RequestMapping("/write")
    public String write(@RequestBody Employee employee) {
        java.util.Map<String,Employee> instanceMap = instance.getMap("employee");    // get map from hazel cast
        instanceMap.put(employee.getId(),employee);                 // write value, This value will be accessible from another jvm also
        return "Employee has been write to Hazelcast";
    }

    @RequestMapping("/read/{id}")
    public Employee read(@PathVariable("id") String id) {
        java.util.Map<String,Employee> instanceMap = instance.getMap("employee");    // get map from hazel cast
        Employee employee= instanceMap.get(id);                    // read value
        return employee;
    }

}

