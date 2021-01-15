FROM openjdk:8
ADD target/springboot-neo4j-docker.jar springboot-neo4j-docker.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","springboot-neo4j-docker.jar"]