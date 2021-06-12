# SPRING BOOT JAVA9 H2DB MULTI MODULE MAVEN EXAMPLE 

---

### To build 
* mvn clean package 

### To Run 
* mvn install && mvn spring-boot:run -pl espark-web
* cd espark-web
    * mvn spring-boot:run 
* java -jar espark-web/target/espark-web-0.0.1-SNAPSHOT.jar

### To Access Application 
* http://localhost:8080/employees
* curl -X GET http://localhost:8080/employees
* curl -X GET http://localhost:8080/api/wish


### log into the homepage of db
* http://localhost:8080/h2-console
* username, pwd , db url and db driver class is mentioned in application.properties file


### Maven dependency tree 
*  mvn dependency:tree -Dverbose


### To View jar content 
* jar tvf espark-web/target/espark-web-1.0-SNAPSHOT.jar