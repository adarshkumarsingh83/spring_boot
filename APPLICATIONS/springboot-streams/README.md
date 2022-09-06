# SPRING BOOT STREAM PRODUCER AND CONSUMERS FOR KAFKA AND RABBITMQ 

----

> this project just post the data to the messag provider rabbit or kafaka 
> and reads it back using listner it has maven profile binded with spring profile to build code 
> for specific messag provider and docker image will build based on that and excuted 



## KAFKA PRODUCER AND CONSUME 

### To Run kakfa locally 

* [downlaod kafka](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka_2.12-2.5.0.tgz)

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
```


* [KAFKA-PRODUCER](./springboot-kafka-rabbitmq-producer/KAFKA-README.md)
* [KAFKA-CONSUMNER](./springboot-kafka-rabbitmq-consumer/KAFKA-README.md)




---

### To run rabbit 
* docker run --name espark-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management



## RABBIT MQ PRODUCER AND CONSUME 
* [RABBIT-PRODUCER](./springboot-kafka-rabbitmq-producer/RABBIT-README.md)
* [RABBIT-CONSUMNER](./springboot-kafka-rabbitmq-consumer/RABBIT-README.md)
