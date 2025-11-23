package com.espark.adarsh.config;

import com.espark.adarsh.service.MyService;
import com.espark.adarsh.service.util.ServiceUtil;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;


public class EsparkBeanRegistrationConfig implements BeanRegistrar {

    @Override
    public void register(BeanRegistry registry, Environment environment) {
        //registry.registerBean(ServiceUtil.class);
        // or
        registry.registerBean(MyService.class,
                p -> p.supplier(supplierContext ->
                        new MyService(supplierContext.bean(ServiceUtil.class))));

       String operation =  Boolean.parseBoolean(environment.getProperty("espark.application.email.enable")) ? "email" :
               Boolean.parseBoolean(environment.getProperty("espark.application.sms.enable")) ? "sms" : "none";

       switch (operation) {
           case "email":
                 registry.registerBean(com.espark.adarsh.service.EmailCommunicationService.class,
                       p -> p.supplier(supplierContext ->
                               new com.espark.adarsh.service.EmailCommunicationService(
                                       supplierContext.bean(ServiceUtil.class))));
               break;
           case "sms":
                registry.registerBean(com.espark.adarsh.service.SmsCommunicationService.class,
                       p -> p.supplier(supplierContext ->
                               new com.espark.adarsh.service.SmsCommunicationService(
                                       supplierContext.bean(ServiceUtil.class))));
               break;
           default:
               registry .registerBean(com.espark.adarsh.service.DefaultCommunicationService.class,
                       p -> p.supplier(supplierContext ->
                               new com.espark.adarsh.service.DefaultCommunicationService(
                                       supplierContext.bean(ServiceUtil.class))));
               break;
       }

    }
}
