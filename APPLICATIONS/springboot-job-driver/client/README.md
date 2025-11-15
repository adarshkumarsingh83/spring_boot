# client 

### To Build 
* mvn clean package 

### To Execute 
* java -jar .\target\client-0.0.1-SNAPSHOT.jar --spring.profiles.active=default "{\"operation\":\"execute\",\"jobType\":\"data-load\"}"
* java -jar .\target\client-0.0.1-SNAPSHOT.jar --spring.profiles.active=default "{\"operation\":\"execute-parallel\",\"jobType\":\"submit-job\"}"
* java -jar .\target\client-0.0.1-SNAPSHOT.jar --spring.profiles.active=default "{\"operation\":\"execute-sequential\",\"jobType\":\"data-load-sequential\"}"

```
* data-load , data-cleanup , submit-data , resubmit-data , fetch-details
{"operation":"execute","jobType":""}

* data-load , data-cleanup , submit-data , resubmit-data , fetch-details
{"operation":"status","jobType":""}

* submit-data , resubmit-data , fetch-details
{"operation":"abort","jobType":""}

* data-load-sequential
{"operation":"execute-sequential","jobType":""}

* submit-job
{"operation":"execute-parallel","jobType":""}


```