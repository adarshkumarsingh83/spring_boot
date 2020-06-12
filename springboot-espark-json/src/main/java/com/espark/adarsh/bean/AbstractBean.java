package com.espark.adarsh.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

public abstract class AbstractBean {

    protected Type type;

    private Date createdOn = new Date();
    private String createdBy = "user";
    private Date updatedOn = new Date();
    private String updatedBy = "user";

    public enum Type {
        user(User.class), address(Address.class);

        Class classType;

        Type(Class classType) {
            if (!AbstractBean.class.isAssignableFrom(classType))
                throw new RuntimeException("class should extend from AbstractNode");
            this.classType = classType;
        }

        public static Type lookupByClassType(Class classType) {
            Type type = Arrays.stream(values()).filter(type1 -> type1.classType == classType).findFirst().orElse(null);
            log.warn("label=typeNotFound classType='{}'", classType);
            return type;
        }

        static Logger log = LoggerFactory.getLogger(Type.class);
    }

    @JsonCreator
    public static AbstractBean create(@JsonProperty("type") Type type) {
        try {
            Constructor<AbstractBean> constructor = null;
            try {
                constructor = type.classType.getConstructor(Type.class);
            } catch (Exception e) {
                constructor = null;
            }
            if (constructor != null) {
                return constructor.newInstance();
            } else {
                return (AbstractBean) type.classType.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException rethrow) {
            throw new RuntimeException(rethrow);
        }
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
