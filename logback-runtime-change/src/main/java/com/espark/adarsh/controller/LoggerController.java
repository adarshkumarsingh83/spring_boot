package com.espark.adarsh.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.espark.adarsh.service.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LoggerController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private List<ch.qos.logback.classic.Logger> loggerList;

    @Autowired
    Services serviceOneImpl;

    @Autowired
    Services serviceTwoImpl;

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerList = loggerContext.getLoggerList();
    }

    @RequestMapping(value = "/{logLevel}",method = RequestMethod.GET)
    public String logMessage(@PathVariable("logLevel") String logLevel){
        logger.info("LogLevel supplied for all {}",logLevel);
        Level level = Level.toLevel(logLevel.toUpperCase());
        loggerList.stream().forEach(tmpLogger -> tmpLogger.setLevel(level));
        return "log level set to "+logLevel;
    }

    @RequestMapping(value = "/{logLevel}",method = RequestMethod.POST)
    public Map<String,String> logLevel(@RequestBody List<String> classNameList, @PathVariable("logLevel") String logLevel){
        logger.info("LogLevel= {} supplied for classes= {}",logLevel,classNameList);
        Level level = Level.toLevel(logLevel.toUpperCase());
        Map<String,String> map=new HashMap<>();
        for(ch.qos.logback.classic.Logger logger: loggerList){
            Iterator<String> iterator=classNameList.iterator();
            while(iterator.hasNext()){
                String name = iterator.next();
                if(logger.getName().endsWith(name)){
                    map.put(logger.getName(),logLevel);
                    logger.setLevel(level);
                    iterator.remove();
                }
            }
        }
        return map;
    }


    @RequestMapping(value = "/trace/{serviceName}",method = RequestMethod.GET)
    public String invokeService(@PathVariable("serviceName") String serviceName){
        logger.info("Invoking Service {}",serviceName);

        if(serviceName.equalsIgnoreCase("ServiceOneImpl")){
            return this.serviceOneImpl.myService();
        }else if(serviceName.equalsIgnoreCase("ServiceTwoImpl")){
            return this.serviceTwoImpl.myService();
        }
        return null;
    }

    @RequestMapping(value = "/beans",method = RequestMethod.GET)
    public List<String> getSpringBeanNames(){
        //return Arrays.asList(applicationContext.getBeanDefinitionNames());
        return loggerList.stream().map(tmpLogger -> tmpLogger.getName()).collect(Collectors.toList());
    }

}
