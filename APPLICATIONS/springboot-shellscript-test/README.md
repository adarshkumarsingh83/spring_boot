# SPRING BOOT SHELL SCRIPT TEST 

---

### To build the code 
* $ mvn clean package 

### To run the code 
* $ mvn spring-boot:run 

### To create s shell script file 
* $ vi url.sh 
```    
#!/bin/bash

index=0
while [ $index -lt 50 ]
do
  echo '\t'
  echo $index
    curl  http://localhost:8080/api/wish;
    sleep 1;
  index=`expr  $index + 1`
done

```
* :wq 
* sh url.sh 