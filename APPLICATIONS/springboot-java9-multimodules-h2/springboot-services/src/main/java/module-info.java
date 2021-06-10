module springboot.services {
    requires org.slf4j;
    requires spring.beans;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires transitive springboot.persistence;
    exports com.espark.adarsh;
    exports com.espark.adarsh.impl;
}