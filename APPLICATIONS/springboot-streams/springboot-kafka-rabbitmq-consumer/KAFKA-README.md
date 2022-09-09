# SPRING BOOT STREAM CONSUMERS FOR KAFKA

---

### To build for kafka
* $ mvn clean package -P kafka
* $ mvn clean package -DskipTests -P kafka

### To run the kafka producer on local
* mvn spring-boot:run -P kafka 

----

# For Container

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for kafka
* $ mvn clean package -DskipTests -P kafka-container

### To build docker image for kafka
* docker build --build-arg JAR_FILE_NAME=kafka-consumer.jar -t adarshkumarsingh83/kafka-consumer .
```
[+] Building 2.3s (8/8) FINISHED                                                                                                                                                                      
 => [internal] load build definition from Dockerfile                                                                                                                                             0.0s
 => => transferring dockerfile: 188B                                                                                                                                                             0.0s
 => [internal] load .dockerignore                                                                                                                                                                0.0s
 => => transferring context: 2B                                                                                                                                                                  0.0s
 => [internal] load metadata for docker.io/library/openjdk:8                                                                                                                                     0.9s
 => [auth] library/openjdk:pull token for registry-1.docker.io                                                                                                                                   0.0s
 => [internal] load build context                                                                                                                                                                0.7s
 => => transferring context: 78.02MB                                                                                                                                                             0.7s
 => CACHED [1/2] FROM docker.io/library/openjdk:8@sha256:86e863cc57215cfb181bd319736d0baf625fe8f150577f9eb58bd937f5452cb8                                                                        0.0s
 => [2/2] ADD target/ springboot-streams.jar                                                                                                                                                     0.3s
 => exporting to image                                                                                                                                                                           0.3s
 => => exporting layers                                                                                                                                                                          0.3s
 => => writing image sha256:3607c3aff0a5282edb30e2e1a16f5b4c1ef3d34677847ae96efbe35e5782a493                                                                                                     0.0s
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