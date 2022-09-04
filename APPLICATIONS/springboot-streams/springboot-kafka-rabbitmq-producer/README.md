# SPRING BOOT STREAM PRODUCERS 

----

### To build for default
* $ mvn clean package


### To build for Rabbitmq
* $ mvn clean package -P rabbit
### To build docker image for rabbit
* docker build --build-arg JAR_FILE_NAME=rabbitmq-producer.jar -t adarshkumarsingh83/rabbitmq-producer .

### To build for kafka
* $ mvn clean package -P kafka
### To build docker image for kafka
* docker build --build-arg JAR_FILE_NAME=kafka-producer.jar -t adarshkumarsingh83/kafka-producer .

### To post daa to the api 
* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":1,"name":"adarsh kumar","message":"love u radha"}'

* $ curl --location --request POST 'http://localhost:8080/api/message' \
--header 'Content-Type: application/json' \
--data-raw '{"id":2,"name":"radha singh","message":"love u adi"} '