# SPRING BOOT LOG SAMPLE

### build the code 
* $ mvn clean package 
* $ mvn spring-boot:run
* $ java -jar target/springboot-haproxy.jar

### To Execute all server 
* sh servers.sh 
* sh servers-stop.sh 


### Manually install in Mac 
````
### To install
$ brew update 
$ brew install haproxy

### To start ther server  
$ sudo haproxy -f ./config/standalone/etc/haproxy/haproxy.cfg

### Service test 
http://localhost/actuator/env
http://localhost/actuator/info
http://localhost/actuator/beans
http://localhost/actuator/metrics
http://localhost/actuator/health

### for admin ui 
http://localhost:9999/stats

### To kill process on port 80 
 $ sudo lsof -i tcp:80
 $ kill -9 

### To unstall 
$ brew uninstall haproxy  

````

````
frontend: defines a reverse proxy which will listen for incoming requests on a specific IP address and port.
backend: defines a pool of servers that the frontend will forward requests to.
listen: a shorthand notation that combines frontend and backend features into a single command.

````


### To Execute ha proxy 
* $ docker run --name espark-haproxy -p80:80 -v $(pwd)/config/docker/etc/haproxy:/usr/local/etc/haproxy:ro haproxy

### To view docker logs 
* $ docker logs --follow  espark-haproxy

### To stop the container 
* $  docker stop  espark-haproxy

* docker ps -a 
* docker container rm <continerid>

### To reload config file 
* docker kill -s HUP espark-haproxy


### TO BUILD & RUN TFS API SERVER VIA DOCKER
* $ docker build -f Dockerfile -t springboot-haproxy .
* $ docker run -e "SPRING_PROFILES_ACTIVE=default" -p 8081:8081 -t springboot-haproxy
* $ docker run -e "SPRING_PROFILES_ACTIVE=default" -p 8082:8082 -t springboot-haproxy
* $ docker run -e "SPRING_PROFILES_ACTIVE=default" -p 8083:8083 -t springboot-haproxy
* $ docker run -e "SPRING_PROFILES_ACTIVE=default" -p 8084:8084 -t springboot-haproxy

### to debug the logs of the docker container 
* $ docker ps -a
* $ docker exec -it <docker-container-name> cat /var/[docker-container-file-system-path]]/logs/application.log > $HOME/spring.log [host-machine-file-system ]
* $ docker exec -it friendly_boyd cat /var/logs/application.log > $HOME/spring.log


### FOR DIRECTLY PRODUCING LOGS TO HOST MACHINE RATHER THEN THE DOCKER CONTAINER 
* $ docker run -v /path/on/host:/path/in/container 
* $ docker container run  -p 8080:8080  -v $HOME/log:/var/logs springboot-haproxy

### To ssh to the container 
* $ docker ps -a 
* $ docker exec -it [container-name] /bin/bash


### FOR TESTING 
* http://localhost:8081/actuator/info
* http://localhost:8082/actuator/info
* http://localhost:8083/actuator/info
* http://localhost:8084/actuator/info

* http://localhost:8080/actuator/health
* http://localhost:8080/actuator/info
* http://localhost:8080/actuator/env
* http://localhost:8080/actuator/beans
* http://localhost:8080/actuator/metrics



### TO DELETE ALL THE CONTAINERS WITH VOLUMES
* $ docker rm -vf $(docker ps -a -q)
### TO DELETE ALL THE IMAGES
* $ docker rmi -f $(docker images -a -q)

---- 


