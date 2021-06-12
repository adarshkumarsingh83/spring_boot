module espark.persistence {

    exports com.espark.adarsh.persistence.entity;
    exports com.espark.adarsh.persistence.respository;
    requires spring.boot;
    requires spring.core;
    requires org.slf4j;
    requires spring.orm;
    requires java.annotation;
    requires spring.beans;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.tx;
    requires spring.jdbc;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires spring.boot.autoconfigure;
    requires java.persistence;
    opens com.espark.adarsh.persistence.service to spring.beans, spring.core;
    opens com.espark.adarsh.persistence.entity to org.hibernate.orm.core, spring.core;
}