# spring boot api server 
----

### To Build and Run the Application
* mvn clean package 

* java -jar target/spring-boot-api-server-0.0.1-SNAPSHOT.jar
* mvn spring-boot:run

### API Endpoints
* curl -X GET http://localhost:8090/api/wish/adarsh
````
{
  "data": {
    "responseMessage": "Hello adarsh, Welcome to Espark Application 2025-10-31T21:27:21.230991"
  },
  "message": "Success"
}
````
