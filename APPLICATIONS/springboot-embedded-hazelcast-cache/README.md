# SPRING BOOT EMBEDDED HAZELCAST CACHE 
> Description: \
> springboot rest api interannly using hazelcast as the cache \
> spring cache manager is use for performing the curd operation on the cache 


----

### To build the code 
* $ mvn clean package 

### To execute application 
* $ java -jar -Dserver.port=9090 target/springboot-embedded-hazelcast-cache.jar
* $ java -jar -Dserver.port=9091 target/springboot-embedded-hazelcast-cache.jar
* $ java -jar -Dserver.port=9092 target/springboot-embedded-hazelcast-cache.jar

### To Test the api on rep cache 
* $ curl -d '{"id":"100","name":"adarsh kumar","email":"adarsh@kumar"}' -H 'Content-Type: application/json' -X POST http://localhost:9090/v1/api/person/espark-rep-cache
* $ curl -d '{"id":"101","name":"amit kumar","email":"amit@kumar"}' -H 'Content-Type: application/json' -X POST http://localhost:9090/v1/api/person/espark-rep-cache
* $ curl -d '{"id":"200","name":"radha singh","email":"radha@singh"}' -H 'Content-Type: application/json' -X POST http://localhost:9090/v1/api/person/espark-rep-cache
* $ curl -X GET http://localhost:9090/v1/api/person/espark-rep-cache/100
* $ curl -X GET http://localhost:9090/v1/api/person/espark-rep-cache/200

### To Test the api on normal cache 
* $ curl -d '{"id":"100","name":"adarsh kumar","email":"adarsh@kumar"}' -H 'Content-Type: application/json' -X POST http://localhost:9090/v1/api/person/espark-cache
* $ curl -d '{"id":"101","name":"amit kumar","email":"amit@kumar"}' -H 'Content-Type: application/json' -X POST http://localhost:9090/v1/api/person/espark-cache
* $ curl -d '{"id":"200","name":"radha singh","email":"radha@singh"}' -H 'Content-Type: application/json' -X POST http://localhost:9090/v1/api/person/espark-cache
* $ curl -X GET http://localhost:9090/v1/api/person/espark-cache/100
* $ curl -X GET http://localhost:9090/v1/api/person/espark-cache/101
* $ curl -X GET http://localhost:9090/v1/api/person/espark-cache/200



### Download management-center:3.12.8 from the official site and execute it 
* hazelcast-management-center-3.12.8$  sh start.sh 

### TO DELETE ALL THE CONTAINERS WITH VOLUMES
* $ docker rm -vf $(docker ps -a -q)
### TO DELETE ALL THE IMAGES
* $ docker rmi -f $(docker images -a -q)


