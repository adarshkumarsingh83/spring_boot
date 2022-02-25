# springboot - welflux mqtt hivemq 

----


### To start the hivemq docker 
* $ docker run -p 9090:8080 -p 1883:1883 hivemq/hivemq4 

## To Open Hivemq Gui
 * http://localhost:9090
 * credentials 
	* User: admin
	* Password: hivemq

# Producer Application 
## [Hive Producer Application](./springboot-welflux-mqtt-producer)

# Consumer Application 
## [Hive Consumer Application](./springboot-welflux-mqtt-consumer)


## Rest End point to publish response 
* $ curl -X GET http://localhost:8080/router/publish/welcome%20to%20espark