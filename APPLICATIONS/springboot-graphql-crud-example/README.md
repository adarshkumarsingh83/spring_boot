# SPRING BOOT-BASIC-CRUD-EXAMPLE

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

## graphql scale lib 
```
	<!--https://github.com/graphql-java/graphql-java-extended-scalars-->
		<dependency>
			<groupId>com.graphql-java</groupId>
			<artifactId>graphql-java-extended-scalars</artifactId>
			<version>20.2</version>
		</dependency>
```

## Graphql console 
* http://localhost:8080/espark/graphiql?path=/api/espark/graphql

### single selection query 
````
query{
  getEmployee(id:1){
    id
    firstName
    lastName
    salary
     doj
    gender
    attributes
    phone
  }
}
````

### multi selection query 
```
query{
  getAllEmployee{
    id
    firstName
    lastName
    salary
     doj
    gender
    attributes
    phone
  }
}
```

### saving data 
```
mutation{
  saveEmployee(employeeBean:{ id:10,firstName:"sonu",lastName:"singh",career:"it", salary: 3, doj:"1010-01-01", gender:MALE, attributes:"{\"key\":\"value\"}", phone:["1234567890","12345678999"]}) {
    id firstName lastName career salary doj gender phone
  }
}
```


### updating data 
```
mutation{
  updateEmployee(employeeBean:{ id:10,firstName:"sonu",lastName:"kumar singh",career:"it", salary: 3}) {
    id firstName lastName career salary
  }
}
```

### Filter Query 
```
query {
  employeesFilter(filter: { salary: { operator: "gt" value: "5" } }) { id firstName lastName salary } 
}
```

### removing data
```
mutation{
  removeEmployee(id: 10){
    id
    firstName
    lastName
    career
  }
}

```


---
# Curl Cmd Operation 

---

### AllEmployee
```
curl --location 'http://localhost:8080/api/espark/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"query{getAllEmployee{  id  firstName  lastName  salary}\n}"}'
```

### Get Employee By Id
```
curl --location 'http://localhost:8080/api/espark/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{
  "query": "query{getEmployee(id:1){  id  firstName  lastName  salary}\n}"
}'
```

### Saving Employee 
```
curl --location 'http://localhost:8080/api/espark/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"mutation{saveEmployee(employeeBean:{ id:10,firstName:\"sonu\",lastName:\"singh\",career:\"it\", salary: 3}) {  id firstName lastName career salary}\n}"}'
```


### Updating Employee 
```
curl --location 'http://localhost:8080/api/espark/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"mutation{updateEmployee(employeeBean:{ id:10,firstName:\"sonu\",lastName:\"kumar singh\",career:\"it\", salary: 3}) {  id firstName lastName career salary}\n}"}'

```


### Filter Query 
```   
curl --location 'http://localhost:8080/api/espark/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"query {\n  employeesFilter(filter: { salary: { operator: \"gt\" value: \"5\" } }) { id firstName lastName salary } \n}"}'
```

### Deleting Employee
```
curl --location 'http://localhost:8080/api/espark/graphql' \
--header 'Content-Type: application/json' \
--data '{
	"query": "mutation{removeEmployee(id: 10){  id  firstName  lastName  career}\n}"
}'
```


## SONAR 
$ Downloads/sonarqube-10.1.0.73491 2/bin/macosx-universal-64

$ sh sonar.sh start
```
$  sh sonar.sh start 
/usr/bin/java
Starting SonarQube...
Started SonarQube.
```
```
$ sh sonar.sh stop 
/usr/bin/javamvn c
Gracefully stopping SonarQube...
Stopped SonarQube.

```
url
http://localhost:9000/
admin/admin

create project manually
analyze your project locally -> provide a token -> generate
it will generate a token
run your project from maven -> it will provide a maven cmd like below to run
and build a code for sonar analysis 

$ mvn clean verify sonar:sonar \
-Dsonar.projectKey=graphql \
-Dsonar.projectName='graphql' \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.token=sqp_d2802588ba05e9255839f21e5ce9bf8d5c84dc8f


