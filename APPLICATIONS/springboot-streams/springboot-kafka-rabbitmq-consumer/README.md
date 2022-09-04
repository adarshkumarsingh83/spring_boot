# SPRING BOOT STREAM CONSUMERS

----

### To build for default
* $ mvn clean package


### To build for Rabbitmq
* $ mvn clean package -P rabbit
### To build docker image for rabbit
* docker build --build-arg JAR_FILE_NAME=rabbitmq-consumer.jar -t adarshkumarsingh83/rabbitmq-consumer .

### To build for kafka
* $ mvn clean package -P kafka
### To build docker image for kafka
* docker build --build-arg JAR_FILE_NAME=kafka-consumer.jar -t adarshkumarsingh83/kafka-consumer .

