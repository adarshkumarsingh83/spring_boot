# SPRINGBOOT JAVA9 H2DB MODULE EXAMPLE 

----


### To Build 
* mvn clean package 


### To Run 
* mvn spring-boot:run


### To Access Application 
* http://localhost:8080/employees
* curl -X GET http://localhost:8080/employees


 ### log into the homepage of db
 * http://localhost:8080/h2-console
 * username, pwd , db url and db driver class is mentioned in application.properties file
 
 
 ### Maven dependency tree 
 *  mvn dependency:tree -Dverbose
 

 ### To View jar content 
 * jar tvf springboot-web/target/springboot-web-1.0-SNAPSHOT.jar