# SPRING BOOT CUSTOM BEAN REGISTRATION EXAMPLE

---

### To Build and Run the Application
* mvn clean package
* java -jar target/spring-boot-custom-bean-registration-0.0.1
* mvn spring-boot:run

### To Test the Application
*  curl -X GET http://localhost:8080/wish

* curl -X GET http://localhost:8080/communicate/message
````
  Email Message Sent: Hello, adarshkumar! Welcome to Espark Application.% 
````
* curl -X GET http://localhost:8080/communicate/message
```
SMS Message Sent: Hello, adarshkumar! Welcome to Espark Application.% 
```
* curl -X GET http://localhost:8080/communicate/message
```
  Default Message Sent: Hello, adarshkumar! Welcome to Espark Application.% 
```