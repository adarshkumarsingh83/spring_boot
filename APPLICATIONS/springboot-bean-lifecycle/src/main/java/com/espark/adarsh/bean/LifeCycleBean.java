package com.espark.adarsh.bean;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.lang.Nullable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class LifeCycleBean implements InitializingBean, DisposableBean,
        ApplicationContextAware, ApplicationEventPublisherAware,
        BeanClassLoaderAware, BeanFactoryAware,
        BeanNameAware, LoadTimeWeaverAware,
        MessageSourceAware, NotificationPublisherAware,
        ResourceLoaderAware , BeanPostProcessor {

    @Value("${espark.adarsh.name}")
    String name;

    @Override
    public void setBeanName(String arg0) {
        // TODO Auto-generated method stub  1
        log.info("BeanNameAware setBeanName()");
    }

    @Override
    public void setBeanClassLoader(ClassLoader arg0) {
        // TODO Auto-generated method stub 2
        log.info("BeanClassLoaderAware setBeanClassLoader()");
    }

    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        // TODO Auto-generated method stub 3
        log.info("BeanFactoryAware setBeanFactory()");
    }

    @Override
    public void setResourceLoader(ResourceLoader arg0) {
        // TODO Auto-generated method stub 4
        log.info("ResourceLoaderAware setResourceLoader()");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher arg0) {
        // TODO Auto-generated method stub 5
        log.info("ApplicationEventPublisherAware setApplicationEventPublisher()");
    }

    @Override
    public void setMessageSource(MessageSource arg0) {
        // TODO Auto-generated method stub 6
        log.info("MessageSourceAware setMessageSource()");
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        // TODO Auto-generated method stub 7
        log.info("ApplicationContextAware setApplicationContext()");
    }

    @PostConstruct
    public void initMethod() {
        //TODO custom init 8
        log.info("custom init()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO InitializingBean 9
        log.info("InitializingBean afterPropertiesSet()");
    }

    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO 10
        log.info("BeanPostProcessor postProcessBeforeInitialization()");
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //TODO 11
        log.info("BeanPostProcessor postProcessAfterInitialization()");
        return bean;
    }

    @PreDestroy
    public void destroyMethod() {
        //TODO custom destroy 12
        log.info("custom destroy()");
    }

    @Override
    public void destroy() throws Exception {
        //TODO DisposableBean 13
        log.info("DisposableBean destroy()");
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher arg0) {
        // TODO only execute in jmx flow
        log.info("NotificationPublisherAware setNotificationPublisher()");
    }

    @Override
    public void setLoadTimeWeaver(LoadTimeWeaver arg0) {
        // TODO  execute in aop flow
        log.info("LoadTimeWeaverAware setLoadTimeWeaver()");
    }
}
