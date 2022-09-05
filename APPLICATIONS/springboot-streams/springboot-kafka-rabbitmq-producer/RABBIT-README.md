# SPRING BOOT STREAM PRODUCERS FOR RABBIT

---

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
  -e RABBITMQ_DESTINATION=   \
  -e RABBITMQ_GROUP=   \
  -e RABBITMQ_HOST=   \
  -e RABBITMQ_PORT=   \
  -e RABBITMQ_USER=   \
  -e RABBITMQ_PASSWORD=   \
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