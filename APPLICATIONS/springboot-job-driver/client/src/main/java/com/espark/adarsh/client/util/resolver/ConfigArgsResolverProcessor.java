package com.espark.adarsh.client.util.resolver;

import com.espark.adarsh.client.annotaton.ConfigArgumentResolver;
import com.espark.adarsh.client.component.resolver.ConfigArgumentsResolver;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@Getter
@Component
public class ConfigArgsResolverProcessor {

    private ConfigurableApplicationContext applicationContext;
    private static final Map<String, Object> configArgsResolvers = new HashMap<>();

    public ConfigArgsResolverProcessor(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        this.loadAnnotatedResolver();
    }

    private void loadAnnotatedResolver() {
        final Map<String, Object> beans =
                applicationContext.getBeanFactory().getBeansWithAnnotation(ConfigArgumentResolver.class);
        for (final Map.Entry<String, Object> bean : beans.entrySet()) {
            final Object object = bean.getValue();
            log.info("ConfigArgsResolverProcessor: loadAnnotatedResolver {}", object);
            configArgsResolvers.put(object.getClass().getAnnotation(ConfigArgumentResolver.class).name(), object);
        }
    }

    public ConfigArgumentsResolver getResolvers(String name) {
        return (ConfigArgumentsResolver) configArgsResolvers.get(name);
    }

    public Supplier<Map<String, Object>> getConfigArgsResolvers = () -> configArgsResolvers;
}
