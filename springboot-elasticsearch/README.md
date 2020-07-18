# SPRING BOOT ELASTIC SEARCH 
> Description:
> spring boot elastic search example 
> where data indexed to the elastic search using 
> spring data repository and then kibina is used to view the indexed data  
> 

### To start the elastic docker 
* docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node"  --name elasticsearch docker.elastic.co/elasticsearch/elasticsearch:7.8.0

### To start the kibana for the elastic search 
* docker run --link elasticsearch -p 5601:5601 docker.elastic.co/kibana/kibana:7.8.0
  
### To compile the application 
* mvn clean package -DskipTests

### To execute the application 
* $ mvn spring-boot:run
* $ java -jar target/springboot-elasticsearch.jar

### To execute application via docker image 
* $ docker build -f Dockerfile -t springboot-elasticsearch .
* $ docker run -e "SPRING_PROFILES_ACTIVE=default" -p 8080:8080 springboot-elasticsearch

### To test the services 
* curl http://localhost:8080/api/employee
* curl http://localhost:8080/api/employee/100
 
