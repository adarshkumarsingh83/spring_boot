# [springboot-redis-cache](https://spring.io/guides/gs/messaging-redis/)
> Description: \
> spring boot redis cache data repository example 

----

### To Execute the Redis docker images
* $  docker run -d -p 6379:6379 --name espark-redis redis
* $  docker ps
* $  docker logs espark-redis
* $  docker exec -it espark-redis sh
* $  redis-cli

````
127.0.0.1:6379> ping
127.0.0.1:6379> set name espark
OK
127.0.0.1:6379> get name
"espark"
127.0.0.1:6379> incr counter
(integer) 1
127.0.0.1:6379> incr counter
(integer) 2
127.0.0.1:6379> get counter
"2"
127.0.0.1:6379> exit
#exit
````

### Connecting from other docker container 
* $ docker run -it --rm --link espark-redis:redis --name espark-client redis sh
*  redis-cli -h redis
````
redis:6379>
redis:6379> get name
"espark"
redis:6379> get counter
"2"
redis:6379> exit
# exit
````

### To clean up the redis docker
```` 
* $ docker stop espark-redis
* $ docker rm espark-redis
* $ docker image ls
* $ docker image rm -f redis
````

----

### Build the application 
* $ mvn clean package -DskipTests 
* $ mvn spring-boot:run 

----

### Api Testing 
* $ curl -d '{"id":"100","userName":"adarsh kumar"}' -H 'Content-Type: application/json' -X POST http://localhost:8080/api/v1/users
* $ curl -d '{"id":"101","userName":"amit kumar"}' -H 'Content-Type: application/json' -X POST http://localhost:8080/api/v1/users
* $ curl -d '{"id":"200","userName":"radha singh"}' -H 'Content-Type: application/json' -X POST http://localhost:8080/api/v1/users
* $ curl -X GET http://localhost:8080/api/v1/users


----
