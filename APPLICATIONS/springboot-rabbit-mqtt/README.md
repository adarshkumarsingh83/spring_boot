# Springboot rabbitmq mqtt 

----

### To Build the code 
* $ mvn clean package 

### To Run the application 
* $ mvn spring-boot:run 

----

### To Start the docker rabbitmq with mqtt 



### To Create a mqtt plugin enabled file

* $ cat >>  /Users/us-guest/rabbitmq/enabled_plugins
```
[rabbitmq_management,rabbitmq_mqtt].

```
* crt+c


### To View the file content
* $ cat /Users/us-guest/rabbitmq/enabled_plugins

### To give permission to the file
* $ sudo chmod -R 777 /Users/us-guest/rabbitmq/enabled_plugins


### To check the permisson
* $  sudo ls -ll  /Users/us-guest/rabbitmq/enabled_plugins

### To run the docker image
```
$ docker run --restart always \
-p 1883:1883  -p 15672:15672  -p 5672:5672 \
-e RABBITMQ_DEFAULT_USER=adarsh \
-e RABBITMQ_DEFAULT_PASS=password \
-v /Users/us-guest/rabbitmq/enabled_plugins:/etc/rabbitmq/enabled_plugins \
-v /Users/us-guest/rabbitmq/:/var/lib/rabbitmq \
--name espark-rabbit \
rabbitmq:3-management
```


### To check the docker container
* $ docker ps -a

### To check the docker logs
$ docker log espark-rabbitmq

### To log into the management console
* http://localhost:15672/#/
* username/pwd

### To Test the publishing using jmosquitto pub util
$ mosquitto_pub -t /espark/adarsh/data  -h localhost -u adarsh -P password -p 1883 -m "welcome from epsark"

### To Test the subscribing using jmosquitto sub util
$ mosquitto_sub -t /espark/adarsh/data -h localhost -u adarsh -P password -p 1883 


### To Test the application hit the api 
``` 
curl -X POST \
http://localhost:8080/espark/mqtt \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{"topic":"/espark/adarsh/data" ,"data":"welcome to the epsark adarsh "}'
```