# application-debugging.sh
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8090,suspend=n -jar ./target/springboot-h2-db-remote-debugging.jar