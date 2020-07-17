# SPRING BOOT LOG SAMPLE

### build the code 
* $ mvn clean packge 
* $ mvn spring-boot:run
* $ java -jar target/springboot-logs.jar


### TO BUILD & RUN TFS API SERVER VIA DOCKER
* $ docker build -f Dockerfile -t springboot-logs .
* $ docker run -e "SPRING_PROFILES_ACTIVE=default" -p 9090:9090 -t springboot-logs

### to debug the logs of the docker container 
* $ docker ps -a
* $ docker exec -it <docker-container-name> cat /var/[docker-container-file-system-path]]/logs/application.log > $HOME/spring.log [host-machine-file-system ]
* $ docker exec -it friendly_boyd cat /var/logs/application.log > $HOME/spring.log


### FOR DIRECTLY PRODUCING LOGS TO HOST MACHINE RATHER THEN THE DOCKER CONTAINER 
* $ docker run -v /path/on/host:/path/in/container 
* $ docker container run  -p 9090:9090  -v $HOME/log:/var/logs springboot-logs

#

### FOR TESTING 
* http://localhost:8080/actuator/health
* http://localhost:8080/actuator/info
* http://localhost:8080/actuator/env
* http://localhost:8080/actuator/beans
* http://localhost:8080/actuator/metrics







