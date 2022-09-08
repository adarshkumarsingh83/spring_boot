# SPRING BOOT STREAM PRODUCERS FOR RABBIT

---

### To build for Rabbitmq
* $ mvn clean package  -P rabbit
* $ mvn clean package -DskipTests -P rabbit

### To run the Rabbit producer on local 
* mvn spring-boot:run -P rabbit
* mvn spring-boot:run -Dspring-boot.run.arguments=--RABBITMQ_DESTINATION=espark_topic,--RABBITMQ_GROUP=espark_group,--RABBITMQ_HOST=localhost,--RABBITMQ_PORT=5672,--RABBITMQ_USER=guest,--RABBITMQ_PASSWORD=guest


### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for Rabbitmq
* $ mvn clean package -DskipTests -P rabbit
### To build docker image for rabbit
* docker build --build-arg JAR_FILE_NAME=rabbitmq-producer.jar  -t adarshkumarsingh83/rabbitmq-producer .

### To run docker
* docker run -p 8080:8080 \
  --name=kafka-producer  \
  --net espark-net  \
  -e RABBITMQ_DESTINATION=espark_topic \
  -e RABBITMQ_GROUP=espark_group   \
  -e RABBITMQ_HOST=localhost \
  -e RABBITMQ_PORT=5672  \
  -e RABBITMQ_USER=guest  \
  -e RABBITMQ_PASSWORD=guest \
  adarshkumarsingh83/rabbitmq-producer

### To push docker image 
* docker push adarshkumarsingh83/rabbitmq-producer

### To post data to the api 
* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":1,"name":"adarsh kumar","message":"love u radha"}'

* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":2,"name":"radha singh","message":"love u adi"} '