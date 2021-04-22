package com.espark.adarsh.service;

import com.espark.adarsh.bean.PrototypeSampleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.lang.Thread.sleep;

@Slf4j
@Async
@Service
public class ApplicationService {

    @Autowired
    ApplicationContext applicationContext;

    public void operator(Integer number) throws InterruptedException {
        for (int i = 0; i < number; i++) {
            PrototypeSampleBean prototypeSampleBean = applicationContext.getBean(PrototypeSampleBean.class);
            sleep(500);
            prototypeSampleBean.setProtoType(false);
        }


        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(allBeanNames).sequential().forEach(bean -> log.info(bean));
    }
}
