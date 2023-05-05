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

## Graphql console 
* http://localhost:8080/graphiql?path=/graphql

### single selection query 
````
query{
  getEmployee(id:1){
    id
    firstName
    lastName
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
  }
}
```

### saving data 
```
mutation{
  saveEmployee(id: 10,firstName: "sonu",lastName:"singh",career: "it"){
    id
    firstName
    lastName
    career
  }
}
```


### updating data 
```
mutation{
  updateEmployee(id: 10,firstName: "sonu",lastName:"singh thakur",career: "it"){
    id
    firstName
    lastName
    career
  }
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
--data '{"query":"{getAllEmployee{id firstName lastName}}"}'
```

### Get Employee By Id
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data '{"query":"{getEmployee(id:1){id firstName lastName}}"}'
```

### Saving Employee 
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data '{
	"query": "mutation{\n  saveEmployee(id: 10,firstName: \"sonu\",lastName:\"singh\",career: \"it\"){\n    id\n    firstName\n    lastName\n    career\n  }\n}"
}'
```


### Updating Employee 
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data '{
	"query": "mutation{\n  updateEmployee(id: 10,firstName: \"sonu\",lastName:\"singh thakur\",career: \"it\"){\n    id\n    firstName\n    lastName\n    career\n  }\n}"
}'
```

### Deleting Employee
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data '{
	"query": "mutation{\n  removeEmployee(id: 10){\n    id\n    firstName\n    lastName\n    career\n  }\n}"
}'
```