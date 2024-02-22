package com.espark.adarsh.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class AnnotationUtil {


    @Qualifier("packageScan")
    @Autowired
    private List<String> packageScan;


    @Qualifier("classAnnotationToScan")
    @Autowired
    private List<String> classAnnotationToScan;


    @Qualifier("methodAnnotationToScan")
    @Autowired
    private List<String> methodAnnotationToScan;


    private Map<Annotation, List<Class>> classAnnotationStore = new HashMap<>();
    private Map<Class, Map<Class, List<Method>>> methodAnnotationStore = new HashMap<>();


    @PostConstruct
    public void init() {
        log.info("Package to scan={}", packageScan);
        log.info("Class Annotation to scan={}", classAnnotationToScan);
        log.info("Method Annotation to scan={}", methodAnnotationToScan);
        List<Class> annotationClass = this.getClassFromName(classAnnotationToScan);
        log.info("Annotation Classes in System={}", annotationClass);
        if (annotationClass != null && !annotationClass.isEmpty()) {
            classAnnotationStore = this.getClassesAnnotation(annotationClass);
            log.info("Annotation and Classes Found classAnnotationStore={}", classAnnotationStore);
        }

        if (classAnnotationStore != null && !classAnnotationStore.isEmpty()) {
            List<Class> annotationMethod = this.getClassFromName(methodAnnotationToScan);
            if (annotationMethod != null && !annotationMethod.isEmpty()) {
                findMethodWithAnnotation(annotationMethod, classAnnotationStore);
                log.info("Annotated Classes And Method Found methodAnnotationStore={}", methodAnnotationStore);
            }
        }
    }


    public List<Class> getClassFromName(List<String> annotatedList) {
        return annotatedList.stream().map(annotationClassName -> {
            Class clazz = null;
            try {
                clazz = Class.forName(annotationClassName);
            } catch (Exception e) {
                log.error("Error while finding annotation from name exception={}", e);
            }
            return clazz;
        }).filter(clazz -> clazz != null).collect(Collectors.toList());
    }


    public Map<Annotation, List<Class>> getClassesAnnotation(List<Class> annotationClass) {
        Map<Annotation, List<Class>> store = new HashMap<>();
        try {
            for (String packageName : packageScan) {
                final ClassPathScanningCandidateComponentProvider scanner =
                        new ClassPathScanningCandidateComponentProvider(true);
                scanner.addIncludeFilter(new AssignableTypeFilter(Object.class));
                final Set<BeanDefinition> beanDefinitionSet = scanner.findCandidateComponents(packageName);
                try {
                    for (BeanDefinition beanDefinition : beanDefinitionSet) {
                        final Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                        this.checkAnnotationExist(clazz, annotationClass, store);
                    }
                } catch (ClassNotFoundException e) {
                    log.error("ClassNotFoundException getClassesAnnotation()  e={}", e);
                }
            }
        } catch (Exception e) {
            log.error("Exception getClassesAnnotation() e={}", e);
        }
        return store;
    }

    private void checkAnnotationExist(
            Class<?> clazz, List<Class> annotationClassSet, Map<Annotation, List<Class>> store) {

        if (annotationClassSet != null && !annotationClassSet.isEmpty()) {
            for (Class annotationClass : annotationClassSet) {
                if (clazz.getAnnotation(annotationClass) != null) {
                    Annotation annotation = clazz.getAnnotation(annotationClass);
                    if (store.containsKey(annotation)) {
                        List<Class> annotationList = store.get(annotation);
                        annotationList.add(clazz);
                        store.put(annotation, annotationList);
                    } else {
                        List<Class> annotationList = new LinkedList<>();
                        annotationList.add(clazz);
                        store.put(annotation, annotationList);
                    }
                }
            }
        }
    }


    private void findMethodWithAnnotation(
            List<Class> methodAnnotationList,
            Map<Annotation, List<Class>> classAnnotationStore) {
        if (classAnnotationStore != null && !classAnnotationStore.isEmpty()) {
            for (Map.Entry<Annotation, List<Class>> annotationClassEntry : classAnnotationStore.entrySet()) {
                List<Class> classList = annotationClassEntry.getValue();
                classList.stream().map(clazz -> {
                    Method[] declaredMethod = clazz.getDeclaredMethods();
                    if (declaredMethod != null) {
                        Map<Class, List<Method>> methodMetaData
                                = findAnnotatedMethod(declaredMethod, methodAnnotationList);
                        if (!methodMetaData.isEmpty()) {
                            return new HashMap<Class, Map<Class, List<Method>>>() {
                                {
                                    put(clazz, methodMetaData);
                                }
                            };
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }).filter((Map map) -> map != null)
                        .forEach(map -> addValueToStore(map));

            }
        }
    }


    private void addValueToStore(Map<Class, Map<Class, List<Method>>> newClasMethodStore) {

        for (Map.Entry<Class, Map<Class, List<Method>>> entryNewClassMethodStore : newClasMethodStore.entrySet()) {

            // class check in existing store
            if (methodAnnotationStore.containsKey(entryNewClassMethodStore.getKey())) {

                Map<Class, List<Method>> existingClassMethodStore =
                        methodAnnotationStore.get(entryNewClassMethodStore.getKey());
                Map<Class, List<Method>> newClassMethodStore = entryNewClassMethodStore.getValue();

                for (Map.Entry<Class, List<Method>> newAnnotationMethodEntry : newClassMethodStore.entrySet()) {

                    //checking for annotation exist or not
                    if (existingClassMethodStore.containsKey(newAnnotationMethodEntry.getKey())) {
                        List<Method> methodList = existingClassMethodStore.get(newAnnotationMethodEntry.getKey());
                        methodList.addAll(newAnnotationMethodEntry.getValue());
                        existingClassMethodStore
                                .put(newAnnotationMethodEntry.getKey(), methodList);
                    } else {
                        existingClassMethodStore
                                .put(newAnnotationMethodEntry.getKey(), newAnnotationMethodEntry.getValue());
                    }
                }
            } else {
                methodAnnotationStore.put(entryNewClassMethodStore.getKey(), entryNewClassMethodStore.getValue());
            }
        }
    }

    private Map<Class, List<Method>> findAnnotatedMethod(
            Method[] declaredMethod, List<Class> methodAnnotationList) {
        Map<Class, List<Method>> store = new HashMap<>();
        List<Method> declaredMethodList = Arrays.asList(declaredMethod);
        for (Method method : declaredMethodList) {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                if (methodAnnotationList.contains(annotation.annotationType())) {
                    if (store.containsKey(annotation)) {
                        List<Method> methodList = store.get(annotation);
                        methodList.add(method);
                        store.put(annotation.annotationType(), methodList);
                    } else {
                        List<Method> methodList = new LinkedList<>();
                        methodList.add(method);
                        store.put(annotation.annotationType(), methodList);
                    }
                }
            }

        }
        return store;
    }
}
