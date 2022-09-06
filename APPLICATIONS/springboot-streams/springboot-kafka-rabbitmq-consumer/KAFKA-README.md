# SPRING BOOT STREAM CONSUMERS FOR KAFKA

---

### To build for kafka
* $ mvn clean package -DskipTests -P kafka

### To run the kafka producer on local
* mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090,--KAFKA_DESTINATION=espark-topic,--KAFKA_GROUP=espark-group,--KAFKA_BORKER=localhost:9092,--ZOOKEEPER_NODE=localhost:2181

----

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for kafka
* $ mvn clean package -DskipTests -P kafka

### To build docker image for kafka
* docker build --build-arg JAR_FILE_NAME=kafka-consumer.jar -t adarshkumarsingh83/kafka-consumer .

### To run docker
* docker run -p 9090:9090 \
  --name=kafka-consumer  \
 --net espark-net  \
 -e JAVA_OPTS=-Dserver.port=9090 \
 -e KAFKA_DESTINATION=espark-topic\
 -e KAFKA_GROUP=espark-group    \
 -e KAFKA_BORKER=localhost:9092   \
 -e ZOOKEEPER_NODE=localhost:2181   \
  adarshkumarsingh83/kafka-consumer

### To push docker image
* docker push adarshkumarsingh83/kafka-consumer