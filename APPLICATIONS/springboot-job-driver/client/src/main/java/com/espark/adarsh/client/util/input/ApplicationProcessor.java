package com.espark.adarsh.client.util.input;

import com.espark.adarsh.client.annotaton.ApiExecution;
import com.espark.adarsh.client.exception.OperationConfigurationNotFound;
import com.espark.adarsh.client.service.ApiExecutionService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.espark.adarsh.client.util.Constants.*;

@Slf4j
@Getter
@Component
public class ApplicationProcessor {

      private InputValidator inputValidator;
      private final ConfigurableApplicationContext applicationContext;
      private static final Map<String, ApiExecutionService> configServiceResolvers = new HashMap<>();

      public ApplicationProcessor(InputValidator inputValidator,
                                  ConfigurableApplicationContext applicationContext) {
            this.inputValidator = inputValidator;
            this.applicationContext = applicationContext;
      }

      @PostConstruct
      public void init() {
            this.loadAnnotatedResolver();
            log.info("loaded all the executor services ");
      }

      private void loadAnnotatedResolver() {
            final Map<String, Object> beans =
                    applicationContext.getBeanFactory().getBeansWithAnnotation(ApiExecution.class);
            for (final Map.Entry<String, Object> bean : beans.entrySet()) {
                  final Object object = bean.getValue();
                  log.info("ApplicationProcessor: loadAnnotatedResolver {}", object);
                  configServiceResolvers.put(object.getClass().getAnnotation(ApiExecution.class).name(), (ApiExecutionService)object);
            }
      }

       private final Function<Map<String, String>,Integer> executeProcess = ( input ) ->{
          String operationType  =  inputValidator.getValidateInput().apply(input);
          String jobType = input.get(JOB_TYPE);
          if( configServiceResolvers.containsKey(operationType) ) {
                return configServiceResolvers.get(operationType).executeApiRequest(jobType);
          }
           throw new OperationConfigurationNotFound(" JobExecutor is not configured for "+jobType);
      };

}
