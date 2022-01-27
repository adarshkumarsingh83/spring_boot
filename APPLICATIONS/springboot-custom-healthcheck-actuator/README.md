# springboot-custom-healthcheck-actuator

### To Build 
* mvn clean package


### To Run 
* mvn spring-boot:run 


### Api Url 
* curl -X GET http://localhost:8080/api/wish
```  
{
  "message": "welcome to the espark "
}  
```
* curl -X GET http://localhost:8080/actuator/health
```    
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "H2",
        "validationQuery": "isValid()"
      }
    },
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 499963174912,
        "free": 305292124160,
        "threshold": 10485760,
        "exists": true
      }
    },
    "integrationService": {
      "status": "UP",
      "details": {
        "Integration-Service": "Available"
      }
    },
    "ping": {
      "status": "UP"
    },
    "wishService": {
      "status": "UP",
      "details": {
        "Wish-Service": "Available"
      }
    }
  }
}
```
* curl -X GET http://localhost:8080/actuator/rest-api
```    
{
    "request-type": "server api get request",
    "server.date": "2022-01-27",
    "server-api": "server api is up and running",
    "server.time": "13:23:03.151125"
}
```
* curl -X POST http://localhost:8080/actuator/rest-api -d 'adarsh kumar'
```
{
    "request-type": "server api post request",
    "server.date": "2022-01-27",
    "server-api": "server api is up and running",
    "server.time": "13:23:24.134326"
}
```


* curl -X GET http://localhost:8080/actuator/server-operation
```   
{
    "operation-type": "server-api-read-operation",
    "server.date": "2022-01-27",
    "server-operation-api": "server api is up and running",
    "server.time": "13:20:40.347791"
}
```
* curl -X DELETE http://localhost:8080/actuator/server-operation
```
{
    "operation-type": "server-api-delete-operation",
    "server.date": "2022-01-27",
    "server-operation-api": "server api is up and running",
    "server.time": "13:20:53.931177"
}  
```
* curl -X POST http://localhost:8080/actuator/server-operation
```
{
    "operation-type": "server-api-write-operation",
    "server.date": "2022-01-27",
    "server-operation-api": "server api is up and running",
    "server.time": "13:21:18.130368"
}
```
