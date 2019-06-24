package com.espark.adarsh.processor;

import com.espark.adarsh.annotation.EnableEspark;
import com.espark.adarsh.annotation.EsparkLowerCase;
import com.espark.adarsh.annotation.EsparkService;
import com.espark.adarsh.annotation.EsparkUpperCase;
import com.espark.adarsh.annotation.EsparkVar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@ConditionalOnBean(annotation = EnableEspark.class)
public class AnnotationProcessor {


    @Autowired
    private ConfigurableApplicationContext context;

    private static List<String> lowerEsparkService = new LinkedList<>();

    private static List<String> upperEsparkService = new LinkedList<>();

    private static Map<String, String> map = new HashMap<>();

    @PostConstruct
    public void init() {
        this.loadAnnotatedService();
    }

    private void loadAnnotatedService() {
        final Map<String, Object> beans = context.getBeanFactory().getBeansWithAnnotation(EsparkService.class);
        for (final Map.Entry<String, Object> bean : beans.entrySet()) {
            final Object object = bean.getValue();
            log.info("Beans {}", object);
            final Method[] methods = object.getClass().getDeclaredMethods();
            for (final Method method : methods) {
                final EsparkUpperCase esparkUpperCase = AnnotationUtils.findAnnotation(method, EsparkUpperCase.class);
                if (esparkUpperCase != null) {
                    String targetServiceId = esparkUpperCase.serviceId();
                    upperEsparkService.add(targetServiceId);
                }

                final EsparkLowerCase esparkLowerCase = AnnotationUtils.findAnnotation(method, EsparkLowerCase.class);
                if (esparkLowerCase != null) {
                    String targetServiceId = esparkLowerCase.serviceId();
                    lowerEsparkService.add(targetServiceId);
                }
            }

            final Field[] fields = object.getClass().getDeclaredFields();
            for (final Field field : fields) {
                final EsparkVar esparkVar = field.getDeclaredAnnotation(EsparkVar.class);
                if (esparkVar != null) {
                    map.put(esparkVar.operation(), field.getName());
                }
            }

        }
    }

    public List<String> getLowerEsparkService() {
        return lowerEsparkService;
    }

    public List<String> getUpperEsparkService() {
        return upperEsparkService;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
