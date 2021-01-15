# SPRING BOOT SSL SERVER CLIENT APPLICATION 
> Description:    \
> spring boot application enabled with ssl  \
> and rest client is https enabled for accessing services 

----
### To delte keystore for ssl 
* $ keytool -delete -noprompt -alias espark  -keystore keystore.jks -storepass esparkpwd

### Create a Keystore for SSL

* $ keytool -genkey -alias espark -keyalg RSA -keysize 2048 -validity 700 -keypass esparkpwd -storepass esparkpwd -keystore keystore.jks
````
What is your first and last name?
  [Unknown]:  localhost
What is the name of your organizational unit?
  [Unknown]:  espark
What is the name of your organization?
  [Unknown]:  espark
What is the name of your City or Locality?
  [Unknown]:  dallas
What is the name of your State or Province?
  [Unknown]:  tx
What is the two-letter country code for this unit?
  [Unknown]:  us
Is CN=localhost, OU=espark, O=espark, L=dallas, ST=tx, C=us correct?
  [no]:  yes


Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore.jks -deststoretype pkcs12".

````
----

### List and Verify Certificate Entry

* $ keytool -list -keystore keystore.jks
````
Enter keystore password:  esparkpwd
Keystore type: jks
Keystore provider: SUN

Your keystore contains 1 entry

espark, Jul 21, 2020, PrivateKeyEntry, 
Certificate fingerprint (SHA1): 86:3E:82:DB:62:78:83:E7:D1:31:8B:ED:0B:25:54:43:FD:FD:36:72

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore.jks -deststoretype pkcs12".

````
----

### Converting to PKCS12 format

* $ keytool -importkeystore -srckeystore keystore.jks -destkeystore espark.p12 -deststoretype pkcs12
````
Importing keystore keystore.jks to espark.p12...
Enter destination keystore password:  esparkpwd
Re-enter new password: esparkpwd
Enter source keystore password:  esparkpwd
Entry for alias espark successfully imported.
Import command completed:  1 entries successfully imported, 0 entries failed or cancelled

````
----

### Import Self Signed Certificate into Java

* $ keytool -exportcert -keystore keystore.jks -alias espark -file espark.cer
````
Enter keystore password:  esparkpwd
Certificate stored in file <espark.cer>

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore.jks -deststoretype pkcs12".

````
----
### Copy the espark.cer to JAVA_HOME/jre/lib/security and execute the below command 

* $ keytool -importcert -keystore cacerts -alias espark -file espark.cer 


#### application.yml
````
# The format used for the keystore. It could be set to JKS in case it is a JKS file
server.ssl.key-store-type: PKCS12
# The path to the keystore containing the certificate
server.ssl.key-store: classpath:esaprk.p12
# The password used to generate the certificate
server.ssl.key-store-password: esparkpwd
# The alias mapped to the certificate
server.ssl.key-alias: espark
````


----
### Build & Execution 
* $ mvn clean package 

### To Execute the application 
* $ java -jar ./springboot-ssl-server/target/springboot-ssl-server.jar
* $ java -jar ./springboot-ssl-client/target/springboot-ssl-client.jar

### To Test the service from server 
- https://localhost:8443/actuator/health
- https://localhost:8443/actuator/info
- https://localhost:8443/actuator/env
- https://localhost:8443/actuator/beans
- https://localhost:8443/actuator/metrics

### To Test the services from client 
* http://localhost:9090/api/list
* http://localhost:9090/api/services/info 
* http://localhost:9090/api/services/env 
* http://localhost:9090/api/services/health 
* http://localhost:9090/api/services/metrics  
* http://localhost:9090/api/services/beans 