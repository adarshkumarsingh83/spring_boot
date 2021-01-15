# SPRING BOOT SSL SERVER CLIENT APPLICATION 
> Description:    \
> spring boot application enabled with ssl  \
> and rest client is https enabled for accessing services 


----

### Generate Self-Signed Certificate

* without localhost 
````
$ keytool -genkeypair -alias espark -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore espark.p12 -validity 3650 -storepass esparkpwd

````
* Example
````
$ cd src/main/resources
$ keytool -genkeypair -alias espark -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore espark.p12 -validity 3650 -storepass esparkpwd
What is your first and last name?
  [Unknown]:  adarsh kumar
What is the name of your organizational unit?
  [Unknown]:  esparkorg
What is the name of your organization?
  [Unknown]:  espark
What is the name of your City or Locality?
  [Unknown]:  dallas
What is the name of your State or Province?
  [Unknown]:  tx
What is the two-letter country code for this unit?
  [Unknown]:  us
Is CN=adarsh kumar, OU=esparkorg, O=espark, L=dallas, ST=tx, C=us correct?
  [no]:  yes

````

* with localhost
````
$ keytool -genkeypair -alias espark -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore espark.p12 -validity 3650 -storepass esparkpwd -ext "SAN:c=DNS:localhost,IP:127.0.0.1"
````
* Example 
````
$ cd src/main/resources
$ keytool -genkeypair -alias espark -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore espark.p12 -validity 3650 -storepass esparkpwd -ext "SAN:c=DNS:localhost,IP:127.0.0.1"
What is your first and last name?
  [Unknown]:  adarsh kumar
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
Is CN=adarsh kumar, OU=espark, O=espark, L=dallas, ST=tx, C=us correct?
  [no]:  yes

````

* to verify the file 
````
$ keytool -list -v -storetype pkcs12 -keystore <YOUR KEYSTORE FILE NAME HERE>.p12
$ keytool -list -v -storetype pkcs12 -keystore espark.p12
````
* Example 
````
$ keytool -list -v -storetype pkcs12 -keystore espark.p12
Enter keystore password:  esparkpwd
Keystore type: PKCS12
Keystore provider: SunJSSE

Your keystore contains 1 entry

Alias name: espark
Creation date: Jul 20, 2020
Entry type: PrivateKeyEntry
Certificate chain length: 1
Certificate[1]:
Owner: CN=adarsh kumar, OU=espark, O=espark, L=dallas, ST=tx, C=us
Issuer: CN=adarsh kumar, OU=espark, O=espark, L=dallas, ST=tx, C=us
Serial number: 34c00821
Valid from: Mon Jul 20 18:33:51 CDT 2020 until: Thu Jul 18 18:33:51 CDT 2030
Certificate fingerprints:
	 MD5:  B3:8F:FC:FF:BF:08:88:86:E3:64:1B:74:22:85:EF:82
	 SHA1: 79:13:A8:14:76:A3:01:7B:91:FF:BA:1F:60:78:A2:2B:09:B1:85:FE
	 SHA256: AA:DC:22:F7:AB:0F:1A:73:F2:1C:5C:55:EC:2C:ED:89:99:7D:94:20:C3:D1:53:C9:CF:B8:08:52:D1:1B:CF:70
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions: 

#1: ObjectId: 2.5.29.17 Criticality=true
SubjectAlternativeName [
  DNSName: localhost
  IPAddress: 127.0.0.1
]

#2: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 83 D0 3A 46 42 E4 7C 96   9F 63 51 18 7B F7 02 4C  ..:FB....cQ....L
0010: DE BA C7 62                                        ...b
]
]

*******************************************
*******************************************
````

### To Enable chrome to trust certificate 

````
hit below url in chrom and enable the 
"Allow invalid certificates for resources loaded from localhost" enabled 
 
chrome://flags/#allow-insecure-localhost

````

* genkeypair: generates a key pair;
* alias: the alias name to access the item in a keystore file. Choose Your own alias name;
* keyalg: the cryptographic algorithm to generate the key pair;
* keysize: the size of the key;
* storetype: the type of keystore;
* keystore: the name of the keystore file;
* validity: the number of days this certificate should be valid;
* storepass: a password to access the keystore file. Choose your own password.

### To Delete the key file for keystore 
````
$ keytool -delete -noprompt -alias ${cert.alias}  -keystore ${keystore.file}  -storepass ${keystore.pass}
$ keytool -delete -noprompt -alias espark  -keystore espark.p12  -storepass esparkpwd

````
----
### Build & Execution 
* $ mvn clean package 

### To Execut the application 
* $ java -jar ./springboot-ssl-server/target/springboot-ssl-server.jar
* $ java -jar ./springboot-ssl-client/target/springboot-ssl-client.jar
