FROM openjdk:8
ADD target/${JAR_FILE_NAME} springboot-streams.jar
EXPOSE 9090
ENTRYPOINT ["java","${JAVA_OPTS}","-jar","springboot-streams.jar"]