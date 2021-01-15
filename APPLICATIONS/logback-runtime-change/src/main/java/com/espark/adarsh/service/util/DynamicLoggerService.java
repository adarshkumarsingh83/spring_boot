package com.espark.adarsh.service.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
public class DynamicLoggerService {

    private List<Logger> loggerList;

    @PostConstruct
    public void init() {
        log.info("label=DynamicLoggerService init()");
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerList = loggerContext.getLoggerList();
    }

    /**
     * getSpringBeanNames() return the list of the classes which are having logger enabled.
     *
     * @return
     */
    public Map<String, String> getSpringBeanNames() {
        final Map<String, String> logEnabledClassWithLogLevel = new LinkedHashMap<>();

        loggerList.stream()
                .filter(logger -> logger != null)
                .forEach(logger -> logEnabledClassWithLogLevel.put(logger.getName(), (logger.getLevel() != null) ? logger.getLevel().toString() : null));
        return logEnabledClassWithLogLevel;
    }

    /**
     * changeLogLevelForAll() this will update the provided log level for each and every class which is enabled with logger
     *
     * @param logLevel
     * @return
     */
    public String changeLogLevelForAll(String logLevel) {
        log.info("label=DynamicLoggerService  changeLogLevelForAll() supplied for all {}", logLevel);
        Level level = Level.toLevel(logLevel.toUpperCase());
        loggerList.stream().forEach(tmpLogger -> tmpLogger.setLevel(level));
        return "log level set to " + logLevel;
    }

    /**
     * changeLogLevelForSelected() this will change the log level for only supplied classes.
     *
     * @param classNameList
     * @param logLevel
     * @return
     */
    public Map<String, String> changeLogLevelForSelected(List<String> classNameList, String logLevel) {
        log.info("label=DynamicLoggerService changeLogLevelForSelected() LogLevel={} supplied for classes={}",
                logLevel, classNameList);
        Level level = Level.toLevel(logLevel.toUpperCase());
        Map<String, String> map = new HashMap<>();
        for (Logger logger : loggerList) {
            Iterator<String> iterator = classNameList.iterator();
            while (iterator.hasNext()) {
                String name = iterator.next();
                if (logger.getName().endsWith(name)) {
                    map.put(logger.getName(), logLevel);
                    logger.setLevel(level);
                    iterator.remove();
                }
            }
        }
        return map;
    }

}
