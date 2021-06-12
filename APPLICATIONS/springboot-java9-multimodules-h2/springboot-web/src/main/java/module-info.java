/**
 *
 */
module springboot.web {
    requires org.slf4j;
    requires spring.web;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.webmvc;
    requires net.bytebuddy;
    requires spring.context;
    requires java.annotation;
    requires java.xml.bind;
    requires java.instrument;
    requires springboot.services;
    requires com.fasterxml.classmate;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens com.espark.adarsh.web;
}