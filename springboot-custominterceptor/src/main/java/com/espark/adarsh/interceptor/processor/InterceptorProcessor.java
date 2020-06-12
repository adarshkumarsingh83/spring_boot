package com.espark.adarsh.interceptor.processor;

import com.espark.adarsh.annotation.EsparkInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InterceptorProcessor implements BeanPostProcessor, WebMvcConfigurer {
    private final Map<HandlerInterceptor,EsparkInterceptor> interceptors = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        this.scanForInterceptorAnnotation(bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String string) throws BeansException {
        return bean;
    }

    protected void scanForInterceptorAnnotation(Object bean, String beanName) {
        Optional<EsparkInterceptor> optionalInterceptor = getInterceptorAnnotation(bean.getClass());
        if (optionalInterceptor.isPresent() && bean instanceof HandlerInterceptor) {
            interceptors.put((HandlerInterceptor) bean, optionalInterceptor.get());
        }
    }

    private Optional<EsparkInterceptor> getInterceptorAnnotation(Class cls) {
        Annotation[] annotations = cls.getAnnotationsByType(EsparkInterceptor.class);
        if (this.hasValue(annotations)) {
            return Optional.of((EsparkInterceptor) annotations[0]);
        }
        return Optional.empty();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.forEach((HandlerInterceptor key, EsparkInterceptor val) -> {
            InterceptorRegistration registration = registry.addInterceptor(key);
            if (this.hasValue(val.pathPatterns())) {
                registration.addPathPatterns(val.pathPatterns());
            }

            if (this.hasValue(val.excludePathPatterns())) {
                registration.excludePathPatterns(val.excludePathPatterns());
            }
        });
    }

    private static <T> boolean hasValue(T[] array) {
        return array != null && array.length > 0;
    }
}
