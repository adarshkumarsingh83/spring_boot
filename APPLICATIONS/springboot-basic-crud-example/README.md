# SPRINGBOOT-BASIC-CRUD-EXAMPLE

---

### to build the application
* $ mvn clean package

### To Run the application
* $ mvn spring-boot:run

### log into the homepage of db
* http://localhost:8080/h2-console
```
username, pwd , dburl and db driver class is mentioned in application.properties file
```

### Get Request 
* http://localhost:8080/employees
* curl --location --request GET 'http://localhost:8080/employees' --header 'Content-Type: application/json' 
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


### Get Request
* http://localhost:8080/employee/1
* curl --location --request GET 'http://localhost:8080/employee/1' 
``` 
 {
  "id": 1,
  "firstName": "adarsh",
  "lastName": "kumar",
  "career": "It"
}
```

### Post Request 
* http://localhost:8080/employee
* curl --location --request POST 'http://localhost:8080/employee' \
  --header 'Content-Type: application/json' \
  --data-raw '{"id": 5,"firstName": "shakti","lastName": "singh","career": "Operatons" }'
``` 
 {
    "id": 5,
    "firstName": "shakti",
    "lastName": "singh",
    "career": "Operatons"
}
```

### Put Request 
* http://localhost:8080/employee/5
* curl --location --request PUT 'http://localhost:8080/employee/5' \
  --header 'Content-Type: application/json' \
  --data-raw '{"id": 5,"firstName": "shakti","lastName": "singh","career": "founder"}'
```
{
    "id": 5,
    "firstName": "shakti",
    "lastName": "singh",
    "career": "founder"
}
```

### Patch Request
* http://localhost:8080/employee/5
* curl --location --request PATCH 'http://localhost:8080/employee/5' \
  --header 'Content-Type: application/json' \
  --data-raw '{"career": "founder-invester"}'
``` 
 {
    "id": 5,
    "firstName": "shakti",
    "lastName": "singh",
    "career": "founder-invester"
}
```