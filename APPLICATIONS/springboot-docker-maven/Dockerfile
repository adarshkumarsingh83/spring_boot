# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="adarshkumarsingh83@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/springboot-docker-maven-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} espark-rest.jar

# Run the jar file 
ENTRYPOINT ["/usr/bin/java","-jar","/espark-rest.jar"]

CMD ["/usr/bin/java","-jar","/espark-rest.jar"]
