# SPRING BOOT STREAM PRODUCERS FOR RABBIT

---

### To build for Rabbitmq
* $ mvn clean package  -P rabbit
* $ mvn clean package -DskipTests -P rabbit

### To run the Rabbit producer on local 
* mvn spring-boot:run -P rabbit

---

# For Container

### To create a new network
* $ docker network create espark-net

### To list the network
* $ docker network list

### To build for Rabbitmq
* $ mvn clean package -DskipTests -P rabbit-container
### To build docker image for rabbit
* docker build --build-arg JAR_FILE_NAME=rabbitmq-producer.jar  -t adarshkumarsingh83/rabbitmq-producer .
```  
[+] Building 2.0s (8/8) FINISHED                                                                                                                                                                                             
 => [internal] load build definition from Dockerfile                                                                                                                                                                    0.0s
 => => transferring dockerfile: 188B                                                                                                                                                                                    0.0s
 => [internal] load .dockerignore                                                                                                                                                                                       0.0s
 => => transferring context: 2B                                                                                                                                                                                         0.0s
 => [internal] load metadata for docker.io/library/openjdk:8                                                                                                                                                            1.4s
 => [auth] library/openjdk:pull token for registry-1.docker.io                                                                                                                                                          0.0s
 => [internal] load build context                                                                                                                                                                                       0.3s
 => => transferring context: 27.38MB                                                                                                                                                                                    0.3s
 => CACHED [1/2] FROM docker.io/library/openjdk:8@sha256:86e863cc57215cfb181bd319736d0baf625fe8f150577f9eb58bd937f5452cb8                                                                                               0.0s
 => [2/2] ADD target/ springboot-streams.jar                                                                                                                                                                            0.1s
 => exporting to image                                                                                                                                                                                                  0.1s
 => => exporting layers                                                                                                                                                                                                 0.1s
 => => writing image sha256:db1df7195a722104de7cbddfb68168ec10e09cf630153d41484b466cb5cb1845                                                                                                                            0.0s
 => => naming to docker.io/adarshkumarsingh83/rabbitmq-producer       
```
### To run docker
* docker run -p 8080:8080 \
  --name=kafka-producer  \
  --net espark-net  \
  -e SPRING_PROFILES_ACTIVE=rabbit-container \
  -e RABBITMQ_DESTINATION=espark_topic \
  -e RABBITMQ_GROUP=espark_group   \
  -e RABBITMQ_HOST=localhost \
  -e RABBITMQ_PORT=5672  \
  -e RABBITMQ_USER=guest  \
  -e RABBITMQ_PASSWORD=guest \
  adarshkumarsingh83/rabbitmq-producer

### To push docker image 
* docker push adarshkumarsingh83/rabbitmq-producer
```
Using default tag: latest
f212a22ea371: Pushed 
6b5aaff44254: Mounted from adarshkumarsingh83/springboot-kubernetes-elk 
53a0b163e995: Mounted from adarshkumarsingh83/springboot-kubernetes-elk 
b626401ef603: Mounted from adarshkumarsingh83/springboot-kubernetes-elk 
9b55156abf26: Mounted from adarshkumarsingh83/springboot-kubernetes-elk 
293d5db30c9f: Mounted from adarshkumarsingh83/springboot-kubernetes-elk 
03127cdb479b: Mounted from adarshkumarsingh83/springboot-kubernetes-elk 
9c742cd6c7a5: Mounted from adarshkumarsingh83/springboot-kubernetes-elk 
latest: digest: sha256:ead30c596b6085ec1c436b73ffca470f98ed62545208a2daf772ec891262e4ac size: 2007   
```
### To post data to the api 
* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":1,"name":"adarsh kumar","message":"love u radha"}'

* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":2,"name":"radha singh","message":"love u adi"} '