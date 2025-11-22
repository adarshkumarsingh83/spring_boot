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
    }
}
