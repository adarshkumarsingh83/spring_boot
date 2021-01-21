# springboot-jpa-h2-testing

> unit testing, mock testing and smoke testing of repository, service and controller 
---

### to build the application
### Docker must be running in local env to build this code 
* $ mvn clean package
* $ mvn spring-boot:run

### log into the homepage of db
* http://localhost:8080/h2-console
* >username, pwd , dburl and db driver class is mention in application.properties file
 
## To test api 

---

### To get all the employee 
* $ curl localhost:8080/api/employees
```
[
  {
    "id": 1,
    "firstName": "adarsh",
    "lastName": "kumar",
    "career": "It"
  },
  {
    "id": 2,
    "firstName": "radha",
    "lastName": "singh",
    "career": "IT"
  },
  {
    "id": 3,
    "firstName": "sonu",
    "lastName": "singh",
    "career": "IT"
  },
  {
    "id": 4,
    "firstName": "amit",
    "lastName": "kumar",
    "career": "Finance"
  }
]
```
### To get only one employee 
* $ curl localhost:8080/api/employee/1
```
{
  "id": 1,
  "firstName": "adarsh",
  "lastName": "kumar",
  "career": "It"
}
```

### To Create a new Employee 
* $ curl localhost:8080/api/employee -X POST -H "Content-Type: application/json" -d '{"firstName": "sonu","lastName": "singh","career": "It"}'
* Response 
```
{
  "id": 5,
  "firstName": "sonu",
  "lastName": "singh",
  "career": "It"
}
```

### To update a existing Employee 
* $ curl localhost:8080/api/employee/5 -X PUT -H "Content-Type: application/json" -d '{"id":5, "firstName": "sonu","lastName": "singh","career": "operation"}'
* Response 
```
{
  "id": 5,
  "firstName": "sonu",
  "lastName": "singh",
  "career": "operation"
}
```

### To delete a Employee 
* $ curl localhost:8080/api/employee/5 -X DELETE
* Response 
```
{
  "id": 5,
  "firstName": "sonu",
  "lastName": "singh",
  "career": "operation"
}
```