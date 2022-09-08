# SPRING BOOT STREAM CONSUMERS FOR RABBIT

----


### To build for Rabbitmq
* $ mvn clean package -P rabbit
* $ mvn clean package -DskipTests -P rabbit

### To run the Rabbit producer on local
* mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090,--RABBITMQ_DESTINATION=espark_topic,--RABBITMQ_GROUP=espark_group,--RABBITMQ_HOST=localhost,--RABBITMQ_PORT=5672,--RABBITMQ_USER=guest,--RABBITMQ_PASSWORD=guest

----

### To build for default
* $ mvn clean package

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list


### To build for Rabbitmq
* $ mvn clean package -DskipTests -P rabbit-container

### To build docker image for rabbit
* docker build --build-arg JAR_FILE_NAME=rabbitmq-consumer.jar  -t adarshkumarsingh83/rabbitmq-consumer .

### To run docker
* docker run -p 9090:9090 \
  --name=kafka-consumer  \
  --net espark-net  \
  -e SPRING_PROFILES_ACTIVE=rabbit-container \
  -e RABBITMQ_DESTINATION=espark_topic \
  -e RABBITMQ_GROUP=espark_group   \
  -e RABBITMQ_HOST=localhost \
  -e RABBITMQ_PORT=5672  \
  -e RABBITMQ_USER=guest  \
  -e RABBITMQ_PASSWORD=guest \
  adarshkumarsingh83/rabbitmq-consumer

### To push docker image
* docker push adarshkumarsingh83/rabbitmq-consumer
