package com.espark.adarsh.client.util.transformer;

import com.espark.adarsh.client.annotaton.RequestTransformer;
import com.espark.adarsh.client.annotaton.ResponseTransformer;
import com.espark.adarsh.client.component.transformer.ApiRequestTransformer;
import com.espark.adarsh.client.component.transformer.ApiResponseTransformer;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@Component
public class TransformerProcessor {

    private ConfigurableApplicationContext applicationContext;
    private static final Map<String, Object> requestTransformer = new HashMap<>();
    private static final Map<String, Object> responseTransformer = new HashMap<>();

    public TransformerProcessor(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        this.loadAnnotatedTransformer();
    }

    private void loadAnnotatedTransformer() {
        final Map<String, Object> requestTransformerBeans =
                applicationContext.getBeanFactory().getBeansWithAnnotation(RequestTransformer.class);
        for (final Map.Entry<String, Object> bean : requestTransformerBeans.entrySet()) {
            final Object object = bean.getValue();
            log.info("TransformerProcessor: loadAnnotatedTransformer Request {}", object);
            requestTransformer.put(object.getClass().getAnnotation(RequestTransformer.class).name(), object);
        }

        final Map<String, Object> responseTransformerBeans =
                applicationContext.getBeanFactory().getBeansWithAnnotation(ResponseTransformer.class);
        for (final Map.Entry<String, Object> bean : responseTransformerBeans.entrySet()) {
            final Object object = bean.getValue();
            log.info("TransformerProcessor: loadAnnotatedTransformer Response {}", object);
            responseTransformer.put(object.getClass().getAnnotation(ResponseTransformer.class).name(), object);
        }
    }


    public static ApiRequestTransformer getRequestTransformer(String name) {
        return (ApiRequestTransformer) requestTransformer.get(name);
    }

    public Supplier<Map<String, Object>> getRequestTransformers = () -> requestTransformer;


    public static ApiResponseTransformer getResponseTransformer(String name) {
        return (ApiResponseTransformer) responseTransformer.get(name);
    }

    public Supplier<Map<String, Object>> getResponseTransformers = () -> responseTransformer;

}
