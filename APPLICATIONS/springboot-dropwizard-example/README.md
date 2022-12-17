## springboot dropwizard example

---
### To build
* mvn clean package

### To Test
* mvn spring-boot:run 
* seq 1 10 | xargs -n1 -P1 curl -X GET  "http://localhost:8080/api/wish/adarsh"

---

### drop wizard end point
* $ curl localhost:8080/metrics