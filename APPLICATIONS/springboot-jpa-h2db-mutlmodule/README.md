# spring boot jpa h2db multi module 

---

### To build 
* mvn clean package 

### To Run 
* mvn install && mvn spring-boot:run -pl spring-web
* cd spring-web
    * mvn spring-boot:run 
* java -jar spring-web/target/spring-web-0.0.1-SNAPSHOT.jar

### To Access Application 
* http://localhost:8080/employees
* curl -X GET http://localhost:8080/employees
* curl -X GET http://localhost:8080/api/wish


### log into the homepage of db
* http://localhost:8080/h2-console
* username, pwd , db url and db driver class is mentioned in application.properties file