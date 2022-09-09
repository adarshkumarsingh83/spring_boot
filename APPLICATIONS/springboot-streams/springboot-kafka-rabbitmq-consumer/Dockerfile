FROM openjdk:8-jdk-alpine
ARG JAR_FILE_NAME=target/*.jar
COPY ${JAR_FILE_NAME} springboot-streams-consumer.jar
EXPOSE 9090
ENTRYPOINT ["echo", "${JAR_FILE_NAME}"]
ENTRYPOINT ["java","-jar","springboot-streams-consumer.jar"]
