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
* http://localhost:8080/graphiql?path=/graphql

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
  }
}
```

### saving data 
```
mutation{
  saveEmployee(employeeBean:{ id:10,firstName:"sonu",lastName:"singh",career:"it", salary: 3, doj:"1010-01-01", gender:MALE, attributes:"{\"key\":\"value\"}" }) {
    id firstName lastName career salary doj gender attributes
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
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"query{getAllEmployee{  id  firstName  lastName  salary}\n}"}'
```

### Get Employee By Id
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{
  "query": "query{getEmployee(id:1){  id  firstName  lastName  salary}\n}"
}'
```

### Saving Employee 
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"mutation{saveEmployee(employeeBean:{ id:10,firstName:\"sonu\",lastName:\"singh\",career:\"it\", salary: 3}) {  id firstName lastName career salary}\n}"}'
```


### Updating Employee 
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"mutation{updateEmployee(employeeBean:{ id:10,firstName:\"sonu\",lastName:\"kumar singh\",career:\"it\", salary: 3}) {  id firstName lastName career salary}\n}"}'

```


### Filter Query 
```   
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"query {\n  employeesFilter(filter: { salary: { operator: \"gt\" value: \"5\" } }) { id firstName lastName salary } \n}"}'
```

### Deleting Employee
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data '{
	"query": "mutation{removeEmployee(id: 10){  id  firstName  lastName  career}\n}"
}'
```