module springboot.persistence {
    exports com.espark.adarsh.entity;
    exports com.espark.adarsh.respository;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires org.slf4j;
    requires spring.orm;
    requires java.annotation;
    requires spring.beans;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.tx;
    requires spring.jdbc;
    requires spring.core;
    requires java.sql;
    requires java.persistence;
}