# SPRING BOOT STREAM CONSUMERS FOR KAFKA

---

### To build for kafka
* $ mvn clean package -P kafka-local
* $ mvn clean package -DskipTests -P kafka-local

### To run the kafka producer on local
* mvn spring-boot:run -P kafka-local

----

# For Container

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for kafka
* $ mvn clean package -DskipTests -P kafka-container

### To build docker image for kafka
* docker build -t adarshkumarsingh83/kafka-consumer --build-arg JAR_FILE_NAME=target/kafka-consumer.jar .
```
[+] Building 2.0s (7/7) FINISHED                                                                                                                                                                      
 => [internal] load build definition from Dockerfile                                                                                                                                             0.0s
 => => transferring dockerfile: 268B                                                                                                                                                             0.0s
 => [internal] load .dockerignore                                                                                                                                                                0.0s
 => => transferring context: 2B                                                                                                                                                                  0.0s
 => [internal] load metadata for docker.io/library/openjdk:8-jdk-alpine                                                                                                                          0.4s
 => [internal] load build context                                                                                                                                                                1.0s
 => => transferring context: 78.00MB                                                                                                                                                             1.0s
 => CACHED [1/2] FROM docker.io/library/openjdk:8-jdk-alpine@sha256:94792824df2df33402f201713f932b58cb9de94a0cd524164a0f2283343547b3                                                             0.0s
 => [2/2] COPY target/kafka-consumer.jar springboot-streams-consumer.jar                                                                                                                         0.2s
 => exporting to image                                                                                                                                                                           0.3s
 => => exporting layers                                                                                                                                                                          0.3s
 => => writing image sha256:5f21df819b93135c19b975d19d34cf7923ae7feeabe4dee2fd8f156e352f0ad1                                                                                                     0.0s
 => => naming to docker.io/adarshkumarsingh83/kafka-consumer           
```
### To run docker
* docker run -p 9090:9090 \
  --name=kafka-consumer  \
 --net espark-net  \
 -e SPRING_PROFILES_ACTIVE=kafka-container \
 -e JAVA_OPTS=-Dserver.port=9090 \
 -e KAFKA_DESTINATION=espark-topic\
 -e KAFKA_GROUP=espark-group    \
 -e KAFKA_BORKER=localhost:9092   \
 -e ZOOKEEPER_NODE=localhost:2181   \
  adarshkumarsingh83/kafka-consumer

### To push docker image
* docker push adarshkumarsingh83/kafka-consumer
```
Using default tag: latest
The push refers to repository [docker.io/adarshkumarsingh83/kafka-consumer]
24fe3b5cbf9e: Pushed 
6b5aaff44254: Mounted from adarshkumarsingh83/kafka-producer 
53a0b163e995: Mounted from adarshkumarsingh83/kafka-producer 
b626401ef603: Mounted from adarshkumarsingh83/kafka-producer 
9b55156abf26: Mounted from adarshkumarsingh83/kafka-producer 
293d5db30c9f: Mounted from adarshkumarsingh83/kafka-producer 
03127cdb479b: Mounted from adarshkumarsingh83/kafka-producer 
9c742cd6c7a5: Mounted from adarshkumarsingh83/kafka-producer 
latest: digest: sha256:0f6a9bb39ff95899ba4647e70ff2a3184fb5ad1d904e1b6e47e4c977e913ec95 size: 2007
```


## To remove image 
* $ docker rmi $(docker images | grep 'adarshkumarsingh83/kafka-consumer')