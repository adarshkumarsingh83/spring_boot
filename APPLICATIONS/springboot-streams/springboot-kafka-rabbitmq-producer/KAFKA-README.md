# SPRING BOOT STREAM PRODUCERS FOR KAFKA

----

### To build for kafka
* $ mvn clean package  -P kafka
* $ mvn clean package -DskipTests -P kafka

### To run the kafka producer on local 
* mvn spring-boot:run -P kafka    
  
---

# For Container

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for kafka
* $ mvn clean package -DskipTests -P kafka-container
 
### To build docker image for kafka
* docker build --build-arg JAR_FILE_NAME=kafka-producer.jar -t adarshkumarsingh83/kafka-producer .
```
[+] Building 4.2s (7/7) FINISHED                                                                                                                                                                                             
 => [internal] load build definition from Dockerfile                                                                                                                                                                    0.0s
 => => transferring dockerfile: 37B                                                                                                                                                                                     0.0s
 => [internal] load .dockerignore                                                                                                                                                                                       0.0s
 => => transferring context: 2B                                                                                                                                                                                         0.0s
 => [internal] load metadata for docker.io/library/openjdk:8                                                                                                                                                            0.6s
 => [internal] load build context                                                                                                                                                                                       1.8s
 => => transferring context: 78.03MB                                                                                                                                                                                    1.8s
 => CACHED [1/2] FROM docker.io/library/openjdk:8@sha256:86e863cc57215cfb181bd319736d0baf625fe8f150577f9eb58bd937f5452cb8                                                                                               0.0s
 => [2/2] ADD target/ springboot-streams.jar                                                                                                                                                                            1.4s
 => exporting to image                                                                                                                                                                                                  0.4s
 => => exporting layers                                                                                                                                                                                                 0.3s
 => => writing image sha256:12c1a37bdc0d05a41689a80670a4d83202f740d575ca626f889b0d11ca44e6ab                                                                                                                            0.0s
 => => naming to docker.io/adarshkumarsingh83/kafka-producer    
```
### To run docker
* docker run -p 8080:8080 \
 --name=kafka-producer  \
 --net espark-net  \
 -e SPRING_PROFILES_ACTIVE=kafka-container \
 -e JAVA_OPTS=-Dserver.port=8080 \
 -e KAFKA_DESTINATION=espark-topic\
 -e KAFKA_GROUP=espark-group    \
 -e KAFKA_BORKER=localhost:9092   \
 -e ZOOKEEPER_NODE=localhost:2181   \
 adarshkumarsingh83/kafka-producer 

### To push docker image
* docker push adarshkumarsingh83/kafka-producer
```
Using default tag: latest
The push refers to repository [docker.io/adarshkumarsingh83/kafka-producer]
60b0ef9f682e: Pushed 
6b5aaff44254: Mounted from adarshkumarsingh83/rabbitmq-producer 
53a0b163e995: Mounted from adarshkumarsingh83/rabbitmq-producer 
b626401ef603: Mounted from adarshkumarsingh83/rabbitmq-producer 
9b55156abf26: Mounted from adarshkumarsingh83/rabbitmq-producer 
293d5db30c9f: Mounted from adarshkumarsingh83/rabbitmq-producer 
03127cdb479b: Mounted from adarshkumarsingh83/rabbitmq-producer 
9c742cd6c7a5: Mounted from adarshkumarsingh83/rabbitmq-producer 
latest: digest: sha256:7e19c6b71fe178c7f6419be4d407f78ceb186a1ed07d35010ba9c2b4406c6eec size: 2007
 
```
### To post daa to the api 
* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":1,"name":"adarsh kumar","message":"love u radha"}'

* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":2,"name":"radha singh","message":"love u adi"} '