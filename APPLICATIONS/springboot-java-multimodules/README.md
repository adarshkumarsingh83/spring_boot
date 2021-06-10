# SPRING BOOT JAVA9 MULTI MODULE MAVEN EXAMPLE 

---

### To build 
* mvn clean package 

### To Run 
* mvn install && mvn spring-boot:run -pl springboot-web
* cd springboot-web
    * mvn spring-boot:run 
* java -jar springboot-web/target/springboot-web-1.0-SNAPSHOT.jar

### To Access Application 
* http://localhost:8080/employees
* curl -X GET http://localhost:8080/employees


#log into the homepage of db
http://localhost:8080/h2-console
* username, pwd , db url and db driver class is mentioned in application.properties file


