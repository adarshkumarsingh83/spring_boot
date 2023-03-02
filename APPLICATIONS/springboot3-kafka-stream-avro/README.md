# SPRING BOOT3 KAFKA STREAM AVRO SCHEMAS 

--- 

### Start Zookeeper
* bin/zookeeper-server-start.sh config/zookeeper.properties

### Start Kafka Server
* bin/kafka-server-start.sh config/server.properties

### Create Kafka Topic
* bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic espark-topic

### Consume from the Kafka Topic via Console
* bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic espark-topic --from-beginning

### Deleting topic
* bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic espark-topic



curl --location --request POST 'http://localhost:8080/api/employee' \
--header 'Content-Type: application/json' \
--data-raw '{
"id":100
,"firstName":"adarsh"
,"lastName":"kumar"
}'