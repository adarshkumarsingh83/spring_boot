# springboot-h2-redis-cache-example

### to build the application
* $ mvn clean package

### To Run the application 
* $ mvn spring-boot:run

### log into the homepage of db
* http://localhost:8080/h2-console
```
username, pwd , dburl and db driver class is mentioned in application.properties file
```

### To run redis cache  
* docker pull redis
* docker run --rm -p 6379:6379  --name redis-espark redis 

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


