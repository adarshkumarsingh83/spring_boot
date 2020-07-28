# SPRING BOOT LOG SAMPLE

### build the code 
* $ mvn clean package 
* $ mvn spring-boot:run
* $ java -jar target/springboot-haproxy.jar

### To Execute all server 
* sh servers.sh 
* sh servers-stop.sh 

### To Execute ha proxy 
* $ docker run --name espark-haproxy -v $(pwd)/haproxy:/usr/local/etc/haproxy:ro haproxy

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


### KUBERNATES BUILD & DOCKER IMAGE CREATION AND PUSH TO DOCKER HUB.
* $ mvn clean package 
* $ docker build -f Dockerfile -t adarshkumarsingh83/springboot-haproxy .
* $ docker container run  -p 8080:8080  -v $HOME/log:/var/logs adarshkumarsingh83/springboot-haproxy
* $ docker push adarshkumarsingh83/springboot-haproxy

* $ kubectl cluster-info
* $ kubectl apply -f $(pwd)/kubernates/app.yml
* $ kubectl get all

### TO VIEW THE LOGS IN THE K8 POD 
* $ kubectl get pod 
* $ kubectl exec -it <pod-name> -- /bin/bash
* $ cd /var/logs
* $ cat application.log


### TO VIEW THE POD DETAILS
$ kubectl get pod

### TO VIEW THE LOGS OF THE POD
$ kubectl logs <pod-name> -f


### TO DELETE DEPLOYMENT & SERVICE 
$ kubectl delete services springboot-haproxy
$ kubectl delete deployment springboot-haproxy






