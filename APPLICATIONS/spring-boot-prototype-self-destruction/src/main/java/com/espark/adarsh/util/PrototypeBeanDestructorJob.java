package com.espark.adarsh.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PrototypeBeanDestructorJob {

    @Scheduled(fixedRate = 1000)
    public void prototypeClearing() {
        List<Object> protoTypeBeanList = DestroyPrototypeBeansPostProcessor.getPrototypeBeans();
        protoTypeBeanList.stream().forEach(bean -> {
            if (bean instanceof PrototypeMarker && !((PrototypeMarker) bean).getProtoType()) {
                if (bean instanceof DisposableBean) {
                    DisposableBean disposable = (DisposableBean) bean;
                    try {
                        log.info("clear prototype {}", bean.hashCode());
                        disposable.destroy();
                    } catch (Exception e) {
                        log.error(e.getLocalizedMessage());
                    }
                }
            }
        });
        protoTypeBeanList.removeIf(bean -> bean instanceof PrototypeMarker && !((PrototypeMarker) bean).getProtoType());
    }
}
