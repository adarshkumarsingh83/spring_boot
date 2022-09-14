# SPRING BOOT STREAM PROCESSOR FOR RABBIT

---

### To build for Rabbitmq
* $ mvn clean package  -P rabbit-local
* $ mvn clean package -DskipTests -P rabbit-local

### To run the Rabbit producer on local 
* mvn spring-boot:run -P rabbit-local

---

# For Container

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for Rabbitmq
* $ mvn clean package -DskipTests -P rabbit-container
### To build docker image for rabbit
* docker build -t adarshkumarsingh83/rabbitmq-processor --build-arg JAR_FILE_NAME=target/rabbitmq-processor.jar .
```  
[+] Building 1.5s (8/8) FINISHED                                                                                                                                                                          
 => [internal] load build definition from Dockerfile                                                                                                                                                 0.0s
 => => transferring dockerfile: 267B                                                                                                                                                                 0.0s
 => [internal] load .dockerignore                                                                                                                                                                    0.0s
 => => transferring context: 2B                                                                                                                                                                      0.0s
 => [internal] load metadata for docker.io/library/openjdk:8-jdk-alpine                                                                                                                              0.9s
 => [auth] library/openjdk:pull token for registry-1.docker.io                                                                                                                                       0.0s
 => [internal] load build context                                                                                                                                                                    0.3s
 => => transferring context: 27.32MB                                                                                                                                                                 0.3s
 => CACHED [1/2] FROM docker.io/library/openjdk:8-jdk-alpine@sha256:94792824df2df33402f201713f932b58cb9de94a0cd524164a0f2283343547b3                                                                 0.0s
 => [2/2] COPY target/rabbitmq-processor.jar springboot-streams-processor.jar                                                                                                                          0.1s
 => exporting to image                                                                                                                                                                               0.1s
 => => exporting layers                                                                                                                                                                              0.1s
 => => writing image sha256:c95761626105f66b026496ecf7f6e4288436d316c99038627f3a416a503435af                                                                                                         0.0s
 => => naming to docker.io/adarshkumarsingh83/rabbitmq-processor        
```
### To run docker
* docker run -p 8080:8080 \
  --name=kafka-processor  \
  --net espark-net  \
  -e SPRING_PROFILES_ACTIVE=rabbit-container \
  -e RABBITMQ_DESTINATION=espark_topic_processed \
  -e RABBITMQ_SOURCE=espark_topic \
  -e RABBITMQ_GROUP=espark_group   \
  -e RABBITMQ_HOST=localhost \
  -e RABBITMQ_PORT=5672  \
  -e RABBITMQ_USER=guest  \
  -e RABBITMQ_PASSWORD=guest \
  adarshkumarsingh83/rabbitmq-producer

### To push docker image 
* docker push adarshkumarsingh83/rabbitmq-processor
```
Using default tag: latest
f212a22ea371: Pushed 
6b5aaff44254: Mounted from adarshkumarsingh83/processor
53a0b163e995: Mounted from adarshkumarsingh83/processor
b626401ef603: Mounted from adarshkumarsingh83/processor 
9b55156abf26: Mounted from adarshkumarsingh83/processor
293d5db30c9f: Mounted from adarshkumarsingh83/processor
03127cdb479b: Mounted from adarshkumarsingh83/processor
9c742cd6c7a5: Mounted from adarshkumarsingh83/processor
latest: digest: sha256:ead30c596b6085ec1c436b73ffca470f98ed62545208a2daf772ec891262e4ac size: 2007   
```

## To remove image
* $ docker rmi $(docker images | grep 'adarshkumarsingh83/rabbit-processor')