package com.espark.adarsh.config;

import static com.espark.adarsh.constant.ApplicationConstant.EXECUTION;
import static com.espark.adarsh.constant.ApplicationConstant.POST_EXECUTION;
import static com.espark.adarsh.constant.ApplicationConstant.POST_TASK;
import static com.espark.adarsh.constant.ApplicationConstant.PRE_EXECUTION;
import static com.espark.adarsh.constant.ApplicationConstant.PRE_TASK;
import static com.espark.adarsh.constant.ApplicationConstant.SEPARATOR_COMMA;
import static com.espark.adarsh.constant.ApplicationConstant.SEPARATOR_EQUALS;
import static com.espark.adarsh.constant.ApplicationConstant.TASK;

import com.espark.adarsh.annotation.ExecutionOperation;
import com.espark.adarsh.annotation.PostExecutionOperation;
import com.espark.adarsh.annotation.PreExecutionOperation;
import com.espark.adarsh.listener.JobCompletionNotificationListener;
import com.espark.adarsh.operation.construct.ExecutionTask;
import com.espark.adarsh.task.OperationPostExecutionTask;
import com.espark.adarsh.task.OperationPreExecutionTask;
import com.espark.adarsh.task.OperationExecutionTask;
import com.espark.adarsh.operation.construct.PostExecutionTask;
import com.espark.adarsh.operation.construct.PreExecutionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;


@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationArguments applicationArguments;

    @Autowired
    private BatchConfigurationValues batchConfigurationValues;

    public Map<String, Object> commandArgs = new HashMap<>();

    private Map<String, LinkedHashMap<String, Class>> operations = null;

    private static LinkedHashMap<String, PreExecutionTask> preExecutionOperations = new LinkedHashMap<>();
    private static LinkedHashMap<String, ExecutionTask> executionOperations = new LinkedHashMap<>();
    private static LinkedHashMap<String, PostExecutionTask> postExecutionOperations = new LinkedHashMap<>();


    @PostConstruct
    public void init() throws Exception {

        // pre=a,b,c execution=x,y  post=p,q
        final String args[] = applicationArguments.getSourceArgs();

        if (args.length == 0) {
            throw new RuntimeException("Command line argument is empty");
        }

        for (String cmd : args) {

            String key = cmd.split(SEPARATOR_EQUALS)[0];
            String[] values = cmd.split(SEPARATOR_EQUALS)[1].split(SEPARATOR_COMMA);
            commandArgs.put(key, Arrays.asList(values));
        }

        if (operations == null) {
            operations = this.scanOperations();
        }

    }

    @Bean
    public Step operationPreExecutionTask() {
        return steps.get("operationPreExecutionTask")
                .tasklet(new OperationPreExecutionTask())
                .build();
    }

    @Bean
    public Step operationExecutionTask() {
        return steps.get("operationExecutionTask")
                .tasklet(new OperationExecutionTask())
                .build();
    }


    @Bean
    public Step operationPostExecutionTask() {
        return steps.get("operationPostExecutionTask")
                .tasklet(new OperationPostExecutionTask())
                .build();
    }


    @Bean
    public Job operationInitTask() {
        return jobs.get("operationInitTask")
                .incrementer(new RunIdIncrementer())
                .start(operationPreExecutionTask())
                .next(operationExecutionTask())
                .next(operationPostExecutionTask())
                .listener(listener())
                .build();
    }

    @Bean
    public JobCompletionNotificationListener listener() {
        return new JobCompletionNotificationListener();
    }


    @Bean("executionOperationsMap")
    public LinkedHashMap<String, ExecutionTask> executionOperationsMap() {
        LinkedHashMap<String, Class> operationClassMap = operations.get(EXECUTION);
        if (operationClassMap != null && !operationClassMap.isEmpty()) {
            for (Map.Entry<String, Class> entry : operationClassMap.entrySet()) {
                Class clazz = entry.getValue();
                ExecutionTask executionTask = (ExecutionTask) applicationContext.getBean(clazz);
                executionOperations.put(entry.getKey(), executionTask);
            }
        }
        return executionOperations;
    }

    @Bean("preExecutionOperationsMap")
    public LinkedHashMap<String, PreExecutionTask> preExecutionOperationsMap() {
        LinkedHashMap<String, Class> operationClassMap = operations.get(PRE_EXECUTION);
        if (operationClassMap != null && !operationClassMap.isEmpty()) {
            for (Map.Entry<String, Class> entry : operationClassMap.entrySet()) {
                Class clazz = entry.getValue();
                PreExecutionTask preExecutionTask = (PreExecutionTask) applicationContext.getBean(clazz);
                preExecutionOperations.put(entry.getKey(), preExecutionTask);
            }
        }
        return preExecutionOperations;
    }

    @Bean("postExecutionOperationsMap")
    public LinkedHashMap<String, PostExecutionTask> postExecutionOperationsMap() {
        LinkedHashMap<String, Class> operationClassMap = operations.get(POST_EXECUTION);
        if (operationClassMap != null && !operationClassMap.isEmpty()) {
            for (Map.Entry<String, Class> entry : operationClassMap.entrySet()) {
                Class clazz = entry.getValue();
                PostExecutionTask postExecutionTask = (PostExecutionTask) applicationContext.getBean(clazz);
                postExecutionOperations.put(entry.getKey(), postExecutionTask);
            }
        }
        return postExecutionOperations;
    }

    /**
     *
     * @return
     */
    private Map<String, LinkedHashMap<String, Class>> scanOperations() {
        Map<String, LinkedHashMap<String, Class>> operations = new HashMap<>();
        LinkedHashMap<String, Class> executionOperations = new LinkedHashMap<>();
        LinkedHashMap<String, Class> preExecutionOperations = new LinkedHashMap<>();
        LinkedHashMap<String, Class> postExecutionOperations = new LinkedHashMap<>();
        String packageName = "com.espark.adarsh.operation";

        List<String> execution = null;
        List<String> pretExecution = null;
        List<String> postExecution = null;

        if (this.commandArgs != null && !this.commandArgs.isEmpty()) {
            execution = (commandArgs.get(TASK) != null) ? (List) commandArgs.get(TASK) : null;
            pretExecution = (commandArgs.get(PRE_TASK) != null) ? (List) commandArgs.get(PRE_TASK) : null;
            postExecution = (commandArgs.get(POST_TASK) != null) ? (List) commandArgs.get(POST_TASK) : null;
        }

        try {
            final ClassPathScanningCandidateComponentProvider scanner =
                    new ClassPathScanningCandidateComponentProvider(true);
            scanner.addIncludeFilter(new AssignableTypeFilter(Object.class));
            final Set<BeanDefinition> beanDefinitionSet = scanner.findCandidateComponents(packageName);
            try {

                for (BeanDefinition beanDefinition : beanDefinitionSet) {
                    final Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                    if (clazz.getAnnotation(PreExecutionOperation.class) != null) {
                        PreExecutionOperation annotation = clazz.getAnnotation(PreExecutionOperation.class);
                        //filter out the based on the cmd line param
                        if (pretExecution != null && !pretExecution.isEmpty()) {
                            if (pretExecution.contains(annotation.name())) {
                                preExecutionOperations.put(annotation.name(), clazz);
                            }
                        } else if (annotation.name().equals(batchConfigurationValues.getValue().getPreTask())) {
                            preExecutionOperations.put(annotation.name(), clazz);
                        }
                    } else if (clazz.getAnnotation(ExecutionOperation.class) != null) {
                        ExecutionOperation annotation = clazz.getAnnotation(ExecutionOperation.class);
                        //filter out the based on the cmd line param
                        if (execution != null && !execution.isEmpty()) {
                            if (execution.contains(annotation.name())) {
                                executionOperations.put(annotation.name(), clazz);
                            }
                        } else if (annotation.name().equals(batchConfigurationValues.getValue().getTask())) {
                            executionOperations.put(annotation.name(), clazz);
                        }
                    } else if (clazz.getAnnotation(PostExecutionOperation.class) != null) {
                        PostExecutionOperation annotation = clazz.getAnnotation(PostExecutionOperation.class);
                        //filter out the based on the cmd line param
                        if (postExecution != null && !postExecution.isEmpty()) {
                            if (postExecution.contains(annotation.name())) {
                                postExecutionOperations.put(annotation.name(), clazz);
                            }
                        } else if (annotation.name().equals(batchConfigurationValues.getValue().getPostTask())) {
                            postExecutionOperations.put(annotation.name(), clazz);
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                log.error("exception e={}", e);
            }
        } catch (Exception e) {
            log.error("exception e={}", e);
        } finally {
            if (!executionOperations.isEmpty()) {
                operations.put(EXECUTION, this.orderOperations(execution, executionOperations));
            }
            if (!preExecutionOperations.isEmpty()) {
                operations.put(PRE_EXECUTION, this.orderOperations(pretExecution, preExecutionOperations));
            }
            if (!postExecutionOperations.isEmpty()) {
                operations.put(POST_EXECUTION, this.orderOperations(postExecution, postExecutionOperations));
            }
        }
        return operations;
    }

    public LinkedHashMap orderOperations(List<String> orderList, LinkedHashMap operations) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (orderList != null && !orderList.isEmpty()) {
            for (String order : orderList) {
                linkedHashMap.put(order, operations.get(order));
            }
            return linkedHashMap;
        }
        return operations;
    }

    public static LinkedHashMap<String, PreExecutionTask> getPreExecutionOperations() {
        return preExecutionOperations;
    }

    public static LinkedHashMap<String, ExecutionTask> getExecutionOperations() {
        return executionOperations;
    }

    public static LinkedHashMap<String, PostExecutionTask> getPostExecutionOperations() {
        return postExecutionOperations;
    }
}