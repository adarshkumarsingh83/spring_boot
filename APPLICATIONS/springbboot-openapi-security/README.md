
# spring boot openapi security

### to build the application
* $ mvn clean package
````
  mvn -X \
  -Djavax.net.debug=ssl \
  -Dmaven.wagon.http.ssl.insecure=true \
  -Dmaven.wagon.http.ssl.allowall=true \
  -Dmaven.wagon.http.ssl.ignore.validity.dates=true \
  clean package
````

### To Run the application 
* $ mvn spring-boot:run

### log into the homepage of db
* http://localhost:8080/h2-console
```
username, pwd , dburl and db driver class is mentioned in application.properties file
```

springdoc.api-docs.path = /espark-openapi

## swagger ui json meta data url
*  http://localhost:8080/espark-openapi/

## swagger ui url
* http://localhost:8080/swagger-ui.html
  * admin/admin

### To Build the docker tag 
* $ docker build -f Dockerfile -t adarshkumarsingh83/springbboot-openapi-security .
```         
[+] Building 2.2s (8/8) FINISHED                                                                                                                                                                      
 => [internal] load build definition from Dockerfile                                                                                                                                             0.0s
 => => transferring dockerfile: 37B                                                                                                                                                              0.0s
 => [internal] load .dockerignore                                                                                                                                                                0.0s
 => => transferring context: 2B                                                                                                                                                                  0.0s
 => [internal] load metadata for docker.io/library/openjdk:8                                                                                                                                     1.3s
 => [auth] library/openjdk:pull token for registry-1.docker.io                                                                                                                                   0.0s
 => [internal] load build context                                                                                                                                                                0.4s
 => => transferring context: 48.72MB                                                                                                                                                             0.4s
 => CACHED [1/2] FROM docker.io/library/openjdk:8@sha256:86e863cc57215cfb181bd319736d0baf625fe8f150577f9eb58bd937f5452cb8                                                                        0.0s
 => [2/2] COPY target/springboot-splunk.jar springboot-splunk.jar                                                                                                                                0.1s
 => exporting to image                                                                                                                                                                           0.2s
 => => exporting layers                                                                                                                                                                          0.2s
 => => writing image sha256:6ef0d66ca9bd6fbb780120063ff99bdcce99a727afee5a852e0f8ed3af628b7c                                                                                                     0.0s
 => => naming to docker.io/adarshkumarsingh83/springboot-splunk     
```
### To push to the docker hub 
* $ docker push adarshkumarsingh83/springbboot-openapi-security

### To Run 
* docker-compose up 
## To stop 
* docker-compose down 

### To Test the Api
* curl -X GET http://localhost:8080/employees