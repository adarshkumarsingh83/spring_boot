# SPRING BOOT STREAM PRODUCERS FOR KAFKA

----

### To build for kafka
* $ mvn clean package -DskipTests -P kafka

### To run the kafka producer on local 
* mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8080,--KAFKA_DESTINATION=,--KAFKA_GROUP=,--KAFKA_BORKER=,--ZOOKEEPER_NODE=  
  
---

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for kafka
* $ mvn clean package -DskipTests -P kafka
 
### To build docker image for kafka
* docker build --build-arg JAR_FILE_NAME=kafka-producer.jar -t adarshkumarsingh83/kafka-producer .

### To run docker
* docker run -p 8080:8080 \
 --name=kafka-producer  \
 --net espark-net  \
 -e JAVA_OPTS=-Dserver.port=8080 \
 -e KAFKA_DESTINATION= \
 -e KAFKA_GROUP=    \
 -e KAFKA_BORKER=   \
 -e ZOOKEEPER_NODE=   \
 adarshkumarsingh83/kafka-producer 

### To push docker image
* docker push adarshkumarsingh83/kafka-producer

### To post daa to the api 
* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":1,"name":"adarsh kumar","message":"love u radha"}'

* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":2,"name":"radha singh","message":"love u adi"} '