package com.espark.adarsh.config;

import com.espark.adarsh.service.MyService;
import com.espark.adarsh.service.util.ServiceUtil;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;


public class EsparkBeanRegistrationConfig implements BeanRegistrar {


    @Override
    public void register(BeanRegistry registry, Environment environment) {

        //registry.registerBean(ServiceUtil.class);
        // or
        
        registry.registerBean(MyService.class,
                p -> p.supplier(supplierContext ->
                        new MyService(supplierContext.bean(ServiceUtil.class))));

        Map<String,Boolean> config = Map.of(
                "email",Boolean.parseBoolean(environment.getProperty("espark.application.communication.email.enable")) ,
                "smd",Boolean.parseBoolean(environment.getProperty("espark.application.communication.sms.enable")),
                "default",Boolean.parseBoolean(environment.getProperty("espark.application.communication.default.enable"))
        );

          config.entrySet()
                 .stream()
                 .forEach(e -> {
                     switch (e.getKey()) {
                         case "email":
                             if(!e.getValue()) {
                                 registry.registerBean("email",com.espark.adarsh.service.EmailCommunicationService.class,
                                         p -> p.supplier(supplierContext ->
                                                 new com.espark.adarsh.service.EmailCommunicationService(
                                                         supplierContext.bean(ServiceUtil.class))));
                             }
                             break;
                         case "sms":
                             if(!e.getValue()) {
                                 registry.registerBean("sms",com.espark.adarsh.service.SmsCommunicationService.class,
                                         p -> p.supplier(supplierContext ->
                                                 new com.espark.adarsh.service.SmsCommunicationService(
                                                         supplierContext.bean(ServiceUtil.class))));
                             }
                             break;
                         default:
                             if(!e.getValue()) {
                                 registry.registerBean("default",com.espark.adarsh.service.DefaultCommunicationService.class,
                                         p -> p.supplier(supplierContext ->
                                                 new com.espark.adarsh.service.DefaultCommunicationService(
                                                         supplierContext.bean(ServiceUtil.class))));
                             }
                             break;
                     }
                 });



    }
}
