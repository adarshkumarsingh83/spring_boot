
# spring boot splunk

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

### To Build the docker tag 
* $ docker build -f Dockerfile -t adarshkumarsingh83/springboot-splunk .
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
* $ docker push adarshkumarsingh83/springboot-splunk

### To Run 
* docker-compose up 
## To stop 
* docker-compose down 

### To Test the Api
* curl -X GET http://localhost:8080/employees

### To login into splunk 
* http://localhost:8000
  * admin/Tiger@007

### Search keyword 
* host=splunkforwarder

## To find the log forwarder to splunk
* Search & Reporting -> Data Summary -> Host -> splunkforwarder



### kill on port 8080
* kill -9 $(lsof -t -i:8080)
-----
# splunk via docker

----
### splunk docker iamge docker hub link
* https://hub.docker.com/r/splunk/splunk/

## Docker cmd to run splunk
* $ docker run -p 8000:8000 -p 8088:8088 -e "SPLUNK_START_ARGS=--accept-license" -e "SPLUNK_PASSWORD=Tiger@001" --name splunk-server splunk/splunk:latest


## splunk gui url
* http://localhost:8000
  * admin/Tiger@001
---
* settings -> DataInputs -> Http Event Collector ->
  * Global Settings
    * AllTokens = enabled
    * Default Source Type = json
    * Default index = main
    * http port no = 8088
      * save
  * New Token
    * name = xxx service
    * source name ovverride = http-event-collector
      * next
    * Input Settings
      * source type
        * new -> source type
          * log4j
      * select allowed index
        * create a new index (name must have underscore)
          * index name = xxxx_envname
            * save
        * select the index created
          * reveiw
            * take value informations  for jave log4j configuraiton
            * submit
* settings -> DataInputs -> Http Event Collector
  * we can see the list of the event collector and token which is used to connect from log4j  


---
### To Test the log is indexing in splunk or not 
* http://splunk_instance:8088/services/collector
* POST REQUEST 
* header
  * "Authorization":"Splunk Token_Value" 
  * "X-Splunk-Request-Channel" : "GUID GENERATED BY OUR SELF FOR UNIQUE IDENTIFICATION" 
* payload {"event":"userid=xxx,activity=running,mode=fast"}

### Request 
````
curl --location --request POST 'http://localhost:8088/services/collector/event' \
--header 'Authorization: Splunk 616a4a62-fc42-4083-b737-330d0106a957' \
--header 'X-Splunk-Request-Channel: d7370678-3b02-11ed-a261-0242ac120002' \
--header 'Content-Type: application/json' \
--data-raw '{"event":"userid=123,activity=running,mode=fast"}'
````
````
curl --location --request POST 'http://localhost:8088/services/collector' \
--header 'Authorization: Splunk b433b295-e619-4f4a-bfb6-9026175c5d6a' \
--header 'X-Splunk-Request-Channel: d7370678-3b02-11ed-a261-0242ac120002' \
--header 'Content-Type: application/json' \
--data-raw '{"event":"userid=123,activity=running,mode=fast"}'
````
### Response 
````
{
"text": "Success",
"code": 0,
"ackId": 1
}
````

### To check weather the log request to the splunk is indexed or not 
* http://splunk_instance:8088/services/collector/ack
* post request 
* payload {"acks":[0,1,2,3,4....N]}
### curl request 
````
curl --location --request POST 'http://localhost:8088/services/collector/ack' \
--header 'Authorization: Splunk b433b295-e619-4f4a-bfb6-9026175c5d6a' \
--header 'X-Splunk-Request-Channel: d7370678-3b02-11ed-a261-0242ac120002' \
--header 'Content-Type: application/json' \
--data-raw '{"acks":[0,1,2,3,4,5,6,7]}'
````
### Response 
```
{
    "acks": {
        "0": true,
        "1": true,
        "2": true,
        "3": true,
        "4": true,
        "5": true,
        "6": true,
        "7": true
    }
}
```
