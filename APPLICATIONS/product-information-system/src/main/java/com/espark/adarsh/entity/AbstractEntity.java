package com.espark.adarsh.entity;

import com.espark.adarsh.exception.ApplicationException;
import org.apache.solr.client.solrj.beans.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.solr.core.mapping.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Field
    @Indexed(name = "type", type = "string")
    protected Type type;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private java.util.Date createdDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private java.util.Date updatedDate;

    @Column(name = "active")
    @Indexed(name = "active", type = "boolean")
    private Boolean active;

    public AbstractEntity(Type type) {
        this.type = type;
        this.active = true;
    }

    public AbstractEntity(Type type, Date createdDate, Date updatedDate) {
        this.type = type;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = true;
    }


    public static enum Type {
        category(Category.class), product(Product.class),
        dimension(Dimension.class), dimensionValue(DimensionValue.class);

        Class classType;

        Type(Class classType) {
            if (!AbstractEntity.class.isAssignableFrom(classType))
                throw new ApplicationException("class should extend from AbstractNode");
            this.classType = classType;
        }

        public static Type lookupByClassType(Class classType) {
            Type type = Arrays.stream(values())
                    .filter(type1 -> type1.classType == classType)
                    .findFirst()
                    .orElse(null);
            log.warn("label=typeNotFound classType='{}'", classType);
            return type;
        }

        public Class getClassType() {
            return classType;
        }

        static Logger log = LoggerFactory.getLogger(Type.class);
    }

    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    public java.util.Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(java.util.Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public abstract Type getType();

    public abstract Long getParent();

    public abstract Long getId();


}
