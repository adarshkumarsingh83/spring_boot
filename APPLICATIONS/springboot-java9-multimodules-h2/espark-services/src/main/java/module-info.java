module espark.services {
    requires org.slf4j;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires java.annotation;
    requires spring.boot.autoconfigure;
    requires transitive espark.persistence;
    exports com.espark.adarsh.services;
    exports com.espark.adarsh.services.impl;

    opens com.espark.adarsh.services.impl to spring.core;
}