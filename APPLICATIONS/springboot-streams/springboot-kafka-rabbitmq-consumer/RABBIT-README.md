# SPRING BOOT STREAM CONSUMERS FOR RABBIT

----


### To build for Rabbitmq
* $ mvn clean package -P rabbit-local
* $ mvn clean package -DskipTests -P rabbit-local

### To run the Rabbit producer on local
* mvn spring-boot:run -P rabbit-local

----

# For Container 

### To build for default
* $ mvn clean package

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list


### To build for Rabbitmq
* $ mvn clean package -DskipTests -P rabbit-container

### To build docker image for rabbit
* docker build -t adarshkumarsingh83/rabbitmq-consumer --build-arg JAR_FILE_NAME=target/rabbitmq-consumer.jar .
``` 
[+] Building 0.9s (7/7) FINISHED                                                                                                                                                                      
 => [internal] load build definition from Dockerfile                                                                                                                                             0.0s
 => => transferring dockerfile: 268B                                                                                                                                                             0.0s
 => [internal] load .dockerignore                                                                                                                                                                0.0s
 => => transferring context: 2B                                                                                                                                                                  0.0s
 => [internal] load metadata for docker.io/library/openjdk:8-jdk-alpine                                                                                                                          0.4s
 => [internal] load build context                                                                                                                                                                0.2s
 => => transferring context: 27.32MB                                                                                                                                                             0.2s
 => CACHED [1/2] FROM docker.io/library/openjdk:8-jdk-alpine@sha256:94792824df2df33402f201713f932b58cb9de94a0cd524164a0f2283343547b3                                                             0.0s
 => [2/2] COPY target/rabbitmq-consumer.jar springboot-streams-consumer.jar                                                                                                                      0.1s
 => exporting to image                                                                                                                                                                           0.1s
 => => exporting layers                                                                                                                                                                          0.1s
 => => writing image sha256:72180d03d4f264b7b6b665e1135005ecfbe99e7567ef46982e1f4b44e7fc8e14                                                                                                     0.0s
 => => naming to docker.io/adarshkumarsingh83/rabbitmq-consumer   
```
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
``` 
Using default tag: latest
The push refers to repository [docker.io/adarshkumarsingh83/rabbitmq-consumer]
d7226814d8d9: Pushed 
6b5aaff44254: Mounted from adarshkumarsingh83/kafka-consumer 
53a0b163e995: Mounted from adarshkumarsingh83/kafka-consumer 
b626401ef603: Mounted from adarshkumarsingh83/kafka-consumer 
9b55156abf26: Mounted from adarshkumarsingh83/kafka-consumer 
293d5db30c9f: Mounted from adarshkumarsingh83/kafka-consumer 
03127cdb479b: Mounted from adarshkumarsingh83/kafka-consumer 
9c742cd6c7a5: Mounted from adarshkumarsingh83/kafka-consumer 
latest: digest: sha256:48394efe790b15f52b6356b491037425df54d2209a31a36dcea0134359842be5 size: 2007
```

## To remove image
* $ docker rmi $(docker images | grep 'adarshkumarsingh83/rabbit-consumer')