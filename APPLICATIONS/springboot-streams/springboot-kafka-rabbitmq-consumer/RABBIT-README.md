# SPRING BOOT STREAM CONSUMERS FOR RABBIT

----

### To build for default
* $ mvn clean package

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list


### To build for Rabbitmq
* $ mvn clean package -DskipTests -P rabbit

### To build docker image for rabbit
* docker build --build-arg JAR_FILE_NAME=rabbitmq-consumer.jar  -t adarshkumarsingh83/rabbitmq-consumer .

### To run docker
* docker run -p 8090:8090 \
  --name=kafka-consumer  \
  --net espark-net  \
  -e RABBITMQ_DESTINATION=   \
  -e RABBITMQ_GROUP=   \
  -e RABBITMQ_HOST=   \
  -e RABBITMQ_PORT=   \
  -e RABBITMQ_USER=   \
  -e RABBITMQ_PASSWORD=   \
  adarshkumarsingh83/rabbitmq-consumer

### To push docker image
* docker push adarshkumarsingh83/rabbitmq-consumer
