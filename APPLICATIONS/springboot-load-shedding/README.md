# Springboot Load Shedding

----

### To build 
* mvn clean package 

### To Test 
* seq 1 10 | xargs -n1 -P5 curl -X GET  "http://localhost:9090/api/wish/adarsh"
