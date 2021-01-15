#!/bin/bash
nohup java -jar -Dserver.port=8081 target/springboot-haproxy.jar > ./log/log8081.log &
nohup java -jar -Dserver.port=8082 target/springboot-haproxy.jar > ./log/log8082.log &
nohup java -jar -Dserver.port=8083 target/springboot-haproxy.jar > ./log/log8083.log &
nohup java -jar -Dserver.port=8084 target/springboot-haproxy.jar > ./log/log8084.log &
echo "server started "&