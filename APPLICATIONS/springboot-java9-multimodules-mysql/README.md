# SPRING BOOT JAVA9 MULTI MODULE MYSQL MAVEN EXAMPLE 

---

### To build 
* mvn clean package 

### To Run 
* mvn install && mvn spring-boot:run -pl espark-web
* cd springboot-web
    * mvn spring-boot:run 
* java -jar espark-web/target/espark-web-0.0.1-SNAPSHOT.jar

### To Access Application 
* http://localhost:8080/employees
* curl -X GET http://localhost:8080/employees
* curl -X GET http://localhost:8080/api/wish

--

## Mysql Docker Image Operation  

### To list the local docker images
* $ docker images

### To pull the mysql image to the local
* $ docker pull mysql

### To run the mysql docker image
* $ docker run --name [mysql-instance-name] -e MYSQL_ROOT_PASSWORD=[root-user-ped] -e MYSQL_DATABASE=[mysql-db-name] -d mysql:[5.6]]
* $ docker run --name espark-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root  -e MYSQL_DATABASE=espark  -d mysql:latest

### Docker container listing
* $ docker container ls

### Docker process list 
* $ docker ps -a

# to delete docker 
$ docker rm -f espark-mysql


