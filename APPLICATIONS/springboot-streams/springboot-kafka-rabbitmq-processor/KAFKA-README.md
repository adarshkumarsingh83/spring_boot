# SPRING BOOT STREAM PROCESSOR FOR KAFKA

----

### To build for kafka
* $ mvn clean package  -P kafka-local
* $ mvn clean package -DskipTests -P kafka-local

### To run the kafka producer on local 
* mvn spring-boot:run -P kafka-local    
  
---

# For Container

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for kafka
* $ mvn clean package -DskipTests -P kafka-container
 
### To build docker image for kafka
* docker build  -t adarshkumarsingh83/kafka-processor --build-arg JAR_FILE_NAME=target/kafka-processor.jar .
```
[+] Building 2.5s (8/8) FINISHED                                                                                                                                                                          
 => [internal] load build definition from Dockerfile                                                                                                                                                 0.0s
 => => transferring dockerfile: 184B                                                                                                                                                                 0.0s
 => [internal] load .dockerignore                                                                                                                                                                    0.0s
 => => transferring context: 2B                                                                                                                                                                      0.0s
 => [internal] load metadata for docker.io/library/openjdk:8                                                                                                                                         1.2s
 => [auth] library/openjdk:pull token for registry-1.docker.io                                                                                                                                       0.0s
 => [internal] load build context                                                                                                                                                                    0.7s
 => => transferring context: 78.04MB                                                                                                                                                                 0.7s
 => CACHED [1/2] FROM docker.io/library/openjdk:8@sha256:86e863cc57215cfb181bd319736d0baf625fe8f150577f9eb58bd937f5452cb8                                                                            0.0s
 => [2/2] ADD  springboot-streams-processor.jar                                                                                                                                                       0.2s
 => exporting to image                                                                                                                                                                               0.3s
 => => exporting layers                                                                                                                                                                              0.3s
 => => writing image sha256:f6833f2882057f42c1d9b64f77d7415cc801a0dcfafc8c4b4d441909b32baf37                                                                                                         0.0s
 => => naming to docker.io/adarshkumarsingh83/kafka-processor    
```
### To run docker
* docker run -p 8080:8080 \
 --name=kafka-processor  \
 --net espark-net  \
 -e SPRING_PROFILES_ACTIVE=kafka-container \
 -e JAVA_OPTS=-Dserver.port=8080 \
 -e KAFKA_DESTINATION=espark-topic-processed\
 -E KAFKA_SOURCE=espark-topic \
 -e KAFKA_GROUP=espark-group    \
 -e KAFKA_BORKER=localhost:9092   \
 -e ZOOKEEPER_NODE=localhost:2181   \
 adarshkumarsingh83/kafka-processor 

### To push docker image
* docker push adarshkumarsingh83/kafka-processor
```
[+] Building 2.0s (7/7) FINISHED                                                                                                                                                                          
 => [internal] load build definition from Dockerfile                                                   0.0s
 => => transferring dockerfile: 37B                                                                                                                                                                  0.0s
 => [internal] load .dockerignore                                                                                                                                                                    0.0s
 => => transferring context: 2B                                                                                                                                                                      0.0s
 => [internal] load metadata for docker.io/library/openjdk:8-jdk-alpine                                                                                                                              0.7s
 => [internal] load build context                                                                                                                                                                    0.6s
 => => transferring context: 78.00MB                                                                                                                                                                 0.6s
 => CACHED [1/2] FROM docker.io/library/openjdk:8-jdk-alpine@sha256:94792824df2df33402f201713f932b58cb9de94a0cd524164a0f2283343547b3                                                                 0.0s
 => [2/2] COPY target/kafka-processor.jar springboot-streams-processor.jar                                                                                                                             0.2s
 => exporting to image                                                                                                                                                                               0.3s
 => => exporting layers                                                                                                                                                                              0.3s
 => => writing image sha256:ce980b8e79aac27b1e7c2a663cd54d3bbff7f962ff5441783889545e5a6fd50d                                                                                                         0.0s
 => => naming to docker.io/adarshkumarsingh83/kafka-processor 
```


## To remove 
* $ docker rmi $(docker images | grep 'adarshkumarsingh83/kafka-processor')