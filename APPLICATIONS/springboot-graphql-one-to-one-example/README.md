# SPRING BOOT-BASIC-CRUD-EXAMPLE

---

### to build the application
* $ mvn clean package
* $ mvn clean install -DskipTests

### To Run the application
* $ mvn spring-boot:run

### log into the homepage of db
* http://localhost:8080/h2-console
```
username, pwd , dburl and db driver class is mentioned in application.properties file
```

## Graphql console 
* http://localhost:8080/graphiql?path=/graphql

### single selection query without dependent data
````
query{
  getEmployee(id:1){
    id
    firstName
    lastName
    salary
  }
}
````

### single selection query with dependent data
``` 
query{
  getEmployee(id:10){
    id
    firstName
    lastName
    salary
    address {
      id
      street
      state
      country
    }
  }
}
```

### multi selection query without dependent data
```
query{
  getAllEmployee{
    id
    firstName
    lastName
    salary
  }
}
```

### multi selection query with dependent data 
```
query{
  getAllEmployee{
    id
    firstName
    lastName
    salary
     address {
      id
      street
      state
      country
    }
  }
}
```

### saving data without dependent data
```
mutation{
  saveEmployee(employeeBean:{ id:10,firstName:"sonu",lastName:"singh",career:"it", salary: 3}) {
    id firstName lastName career salary
  }
}
```

### saving data with dependent data
```
mutation{
  saveEmployee(employeeBean:{ id:50,firstName:"sonu",lastName:"singh",career:"it", salary: 3 , addressBean:{ id:5, street:"blvd street",state:"texas",country:"usa"}}) {
    id firstName lastName career salary address{ id street state country}
  }
}
```

### updating data without dependent data 
```
mutation{
  updateEmployee(employeeBean:{ id:10,firstName:"sonu",lastName:"kumar singh",career:"it", salary: 3}) {
    id firstName lastName career salary
  }
}
```

### updating data with dependent data
```
mutation{
  updateEmployee(employeeBean:{ id:50,firstName:"sonu",lastName:"kumar singh",career:"it", salary: 3 , addressBean:{ id:1, street:"town and country blvd street",state:"texas",country:"usa"}}) {
    id firstName lastName career salary address{ id street state country}
  }
}
```

### Filter Query without dependent 
```
query {
  employeesFilter(filter: { salary: { operator: "gt" value: "5" } }) { id firstName lastName salary } 
}
```

### Filter Query with dependent
```
query {
  employeesFilter(filter: { salary: { operator: "gt" value: "5" } }) { id firstName lastName salary address {id street state country}} 
}
```

### removing data
```
mutation{
  removeEmployee(id: 1){
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

### AllEmployee without Dependent 
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"query{getAllEmployee{  id  firstName  lastName  salary}\n}"}'

```

### AllEmployee with Dependent
```

curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"query{getAllEmployee{  id  firstName  lastName  salary  address {id street state country }}\n}"}'
```

### Get Employee By Id without Dependent
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{
  "query": "query{getEmployee(id:1){  id  firstName  lastName  salary}\n}"
}'
```

### Get Employee By Id with Dependent
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{
  "query": "query{getEmployee(id:10){  id  firstName  lastName  salary  address {id street state country }}\n}"
}'
```


### Saving Employee without Dependent
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"mutation{saveEmployee(employeeBean:{ id:10,firstName:\"sonu\",lastName:\"singh\",career:\"it\", salary: 3}) {  id firstName lastName career salary}\n}"}'
```

### Saving Employee with Dependent
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"mutation{saveEmployee(employeeBean:{ id:50,firstName:\"sonu\",lastName:\"singh\",career:\"it\", salary: 3  addressBean:{ id:5, street:\"blvd street\",state:\"texas\",country:\"usa\"}}) {  id firstName lastName career salary address {id street state country }}\n}"}'

```

### Updating Employee without Dependent
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"mutation{updateEmployee(employeeBean:{ id:50,firstName:\"sonu\",lastName:\"kumar singh\",career:\"it\", salary: 3 }) {  id firstName lastName career salary }\n}"}'

```


### Updating Employee with Dependent
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"mutation{updateEmployee(employeeBean:{ id:50,firstName:\"sonu\",lastName:\"kumar singh\",career:\"it\", salary: 3 , addressBean:{ id:1, street:\"town and country blvd street\",state:\"texas\",country:\"usa\"}}) {  id firstName lastName career salary address {id street state country }}\n}"}'
```


### Filter Query without dependent 
```   
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"query {\n  employeesFilter(filter: { salary: { operator: \"gt\" value: \"5\" } }) { id firstName lastName salary } \n}"}'
```

### Filter Query without dependent
```   
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--header 'Cookie: cookieName=' \
--data '{"query":"query {\n  employeesFilter(filter: { salary: { operator: \"gt\" value: \"5\" } }) { id firstName lastName salary address {id street state country} } \n}"}'
```

### Deleting Employee
```
curl --location 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data '{
	"query": "mutation{removeEmployee(id: 10){  id  firstName  lastName  career}\n}"
}'
```