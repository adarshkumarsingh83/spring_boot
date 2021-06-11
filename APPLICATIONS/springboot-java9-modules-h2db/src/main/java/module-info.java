module springboot.java9.modules.h2db {

    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.tx;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.webmvc;
    requires java.xml.bind;
    requires java.sql;
    requires spring.jdbc;
    requires java.annotation;
    requires java.persistence;
    requires com.fasterxml.classmate;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens com.espark.adarsh;
}