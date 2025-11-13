# client 

### To Build 
* mvn clean package 

### To Execute 
* java -jar .\target\client-0.0.1-SNAPSHOT.jar --spring.profiles.active=default "{\"operation\":\"create\",\"jobType\":\"data-load\"}"

```
* data-load , data-cleanup , submit-data , resubmit-data , fetch-details
{"operation":"create","jobType":""}
{"operation":"status","jobType":""}
{"operation":"abort","jobType":""}


```