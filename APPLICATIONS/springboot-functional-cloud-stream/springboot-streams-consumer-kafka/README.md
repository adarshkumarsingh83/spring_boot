# springboot kafka stream consumer
---


### To Run kafka locally

* [download kafka](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka_2.12-2.5.0.tgz)

```
Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

Start Kafka Server
bin/kafka-server-start.sh config/server.properties

Create Kafka Topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic espark-topic

Consume from the Kafka Topic via Console
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic espark-topic --from-beginning

Deleting topic
bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic espark-topic


## To use default producer for testing 
./bin/kafka-console-produer.sh --bootstrap-server localhost:9092 --topic espark-topic  

## To use default consumer for testing 
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic espark-topic

```


### To Build Code 
* mvn clean package -DskipTests

### To Run the Code 
* mvn spring-boot:run 

