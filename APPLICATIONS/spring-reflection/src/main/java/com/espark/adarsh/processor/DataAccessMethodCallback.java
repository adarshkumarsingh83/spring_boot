package com.espark.adarsh.processor;

import com.espark.adarsh.processor.annotation.DataAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.*;

public class DataAccessMethodCallback implements ReflectionUtils.MethodCallback {

    private static Logger logger = LoggerFactory.getLogger(DataAccessMethodCallback.class);

    private static int AUTOWIRE_MODE = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;

    private static String ERROR_ENTITY_VALUE_NOT_SAME = "@DataAccess(entity) "
            + "value should have same type with injected generic type.";
    private static String WARN_NON_GENERIC_VALUE = "@DataAccess annotation assigned "
            + "to raw (non-generic) declaration. This will make your code less type-safe.";
    private static String ERROR_CREATE_INSTANCE = "Cannot create instance of "
            + "type '{}' or instance creation is failed because: {}";

    private ConfigurableListableBeanFactory configurableBeanFactory;
    private Object bean;

    public DataAccessMethodCallback(ConfigurableListableBeanFactory bf, Object bean) {
        configurableBeanFactory = bf;
        this.bean = bean;
    }

    @Override
    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {

        // @DataAccess processing
        if (method.isAnnotationPresent(DataAccess.class)) {
            ReflectionUtils.makeAccessible(method);

            Type[] methodParamType = method.getGenericParameterTypes();
            Class<?> classValue = method.getDeclaredAnnotation(DataAccess.class).entity();

            Type fieldGenericType = methodParamType[0];

            // get actual "GenericDAO' type.
            Class<?> generic = ((ParameterizedTypeImpl) methodParamType[0]).getRawType();

            if (genericTypeIsValid(classValue, fieldGenericType)) {
                String beanName = classValue.getSimpleName() + generic.getSimpleName();
                Object beanInstance = getBeanInstance(beanName, generic, classValue);
                try {
                    method.invoke(bean,beanInstance);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new IllegalArgumentException(ERROR_ENTITY_VALUE_NOT_SAME);
            }

        } else {
            return;
        }
    }

    public boolean genericTypeIsValid(Class<?> clazz, Type field) {
        if (field instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) field;
            Type type = parameterizedType.getActualTypeArguments()[0];
            return type.equals(clazz);
        } else {
            logger.warn(WARN_NON_GENERIC_VALUE);
            return true;
        }
    }

    public Object getBeanInstance(
            String beanName, Class<?> genericClass, Class<?> paramClass) {
        Object daoInstance = null;
        if (!configurableBeanFactory.containsBean(beanName)) {
            logger.info("Creating new DataAccess bean named '{}'.", beanName);

            Object toRegister = null;
            try {
                Constructor<?> ctr = genericClass.getConstructor(Class.class);
                toRegister = ctr.newInstance(paramClass);
            } catch (Exception e) {
                logger.error(ERROR_CREATE_INSTANCE, genericClass.getTypeName(), e);
                throw new RuntimeException(e);
            }

            daoInstance = configurableBeanFactory.initializeBean(toRegister, beanName);
            configurableBeanFactory.autowireBeanProperties(daoInstance, AUTOWIRE_MODE, true);
            configurableBeanFactory.registerSingleton(beanName, daoInstance);
            logger.info("Bean named '{}' created successfully.", beanName);
        } else {
            daoInstance = configurableBeanFactory.getBean(beanName);
            logger.info(
                    "Bean named '{}' already exists used as current bean reference.", beanName);
        }
        return daoInstance;
    }
}