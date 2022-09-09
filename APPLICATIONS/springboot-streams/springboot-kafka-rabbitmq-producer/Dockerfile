FROM openjdk:8-jdk-alpine
ARG JAR_FILE_NAME=target/*.jar
COPY ${JAR_FILE_NAME} springboot-streams-producer.jar
EXPOSE 8080
ENTRYPOINT ["echo", "${JAR_FILE_NAME}"]
ENTRYPOINT ["java","-jar","springboot-streams-producer.jar"]