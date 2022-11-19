# springboot-sonaarquibe 

---

### To Build 
* mvn clean package 

### To run 
* mvn spring-boot:run 

### To access api 
* $ curl http://localhost:8080/api/wish/adarsh
```
{"connected at ":"23:39","name":"welcome to espark adarsh"}
```

### To generate code coverage report by Jacoco
* mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install

### To download sonar 
* https://www.sonarqube.org/downloads/
* opt for "Community Edition"

### To Start sonarquibe server 
* cd sonarqube-9.7.1.62043/bin/macosx-universal-64
* to start sonarquibe server
    * $ sh sonar.sh start
```  
/usr/bin/java
Starting SonarQube...
Started SonarQube.
```  

* To login into the sonarquibe server 
  * http://localhost:9000
    * admin/admin
    * changed pwd it will use for next time login  after server start

* create project manually 
* provide the project name "spring-boot-sonarquibe" click on setup 
* select the option locally for the code 
* provided a token genenrate the projct token 
  * copy the token generated for the project name provided earleir "spring-boot-sonarquibe"
```
Analyze "spring-boot-sonarquibe": sqp_02213f86c6ac6e4583098085732527045276796a
```
* click on continue 
* select run analysis on project maven option 
  * copy the maven cmd for the project 
``` 
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=spring-boot-sonarquibe \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=sqp_02213f86c6ac6e4583098085732527045276796a
```
* execute the mvn cmd on the project root dir from terminal 

``` 
[INFO] --------------< com.espark.adarsh:spring-boot-sonarquibe >--------------
[INFO] Building spring-boot-sonarquibe 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- sonar-maven-plugin:3.8.0.2131:sonar (default-cli) @ spring-boot-sonarquibe ---
[INFO] User cache: /Users/us-guest/.sonar/cache
[INFO] SonarQube version: 9.7.1.62043
[INFO] Default locale: "en_US", source code encoding: "UTF-8"
[INFO] Load global settings
[INFO] Load global settings (done) | time=94ms
[INFO] Server id: 147B411E-AYSOawLuTukZ56DVsNDw
[INFO] User cache: /Users/us-guest/.sonar/cache
[INFO] Load/download plugins
[INFO] Load plugins index
[INFO] Load plugins index (done) | time=63ms
[INFO] Load/download plugins (done) | time=154ms
[INFO] Process project properties
[INFO] Process project properties (done) | time=8ms
[INFO] Execute project builders
[INFO] Execute project builders (done) | time=1ms
[INFO] Project key: spring-boot-sonarquibe
[INFO] Base dir: /Users/us-guest/Downloads/spring-boot-sonarquibe
[INFO] Working dir: /Users/us-guest/Downloads/spring-boot-sonarquibe/target/sonar
[INFO] Load project settings for component key: 'spring-boot-sonarquibe'
[INFO] Load project settings for component key: 'spring-boot-sonarquibe' (done) | time=37ms
[WARNING] SCM provider autodetection failed. Please use "sonar.scm.provider" to define SCM of your project, or disable the SCM Sensor in the project settings.
[INFO] Load quality profiles
[INFO] Load quality profiles (done) | time=102ms
[INFO] Load active rules
[INFO] Load active rules (done) | time=1630ms
[INFO] Load analysis cache
[INFO] Load analysis cache (404) | time=8ms
[INFO] Load project repositories
[INFO] Load project repositories (done) | time=17ms
[INFO] Indexing files...
[INFO] Project configuration:
[INFO] 4 files indexed
[INFO] Quality profile for java: Sonar way
[INFO] Quality profile for xml: Sonar way
[INFO] ------------- Run sensors on module spring-boot-sonarquibe
[INFO] Load metrics repository
[INFO] Load metrics repository (done) | time=16ms
[INFO] Sensor JavaSensor [java]
[INFO] Configured Java source version (sonar.java.source): 8
[INFO] JavaClasspath initialization
[INFO] JavaClasspath initialization (done) | time=14ms
[INFO] JavaTestClasspath initialization
[INFO] JavaTestClasspath initialization (done) | time=2ms
[INFO] Server-side caching is enabled. The Java analyzer will not try to leverage data from a previous analysis.
[INFO] Using ECJ batch to parse 2 Main java source files with batch size 214 KB.
[INFO] Starting batch processing.
[INFO] The Java analyzer cannot skip unchanged files in this context. A full analysis is performed for all files.
[INFO] 100% analyzed
[INFO] Batch processing: Done.
[INFO] Did not optimize analysis for any files, performed a full analysis for all 2 files.
[INFO] Using ECJ batch to parse 1 Test java source files with batch size 214 KB.
[INFO] Starting batch processing.
[INFO] 100% analyzed
[INFO] Batch processing: Done.
[INFO] Did not optimize analysis for any files, performed a full analysis for all 1 files.
[INFO] No "Generated" source files to scan.
[INFO] Sensor JavaSensor [java] (done) | time=888ms
[INFO] Sensor JaCoCo XML Report Importer [jacoco]
[INFO] 'sonar.coverage.jacoco.xmlReportPaths' is not defined. Using default locations: target/site/jacoco/jacoco.xml,target/site/jacoco-it/jacoco.xml,build/reports/jacoco/test/jacocoTestReport.xml
[INFO] Importing 1 report(s). Turn your logs in debug mode in order to see the exhaustive list.
[INFO] Sensor JaCoCo XML Report Importer [jacoco] (done) | time=15ms
[INFO] Sensor CSS Rules [javascript]
[INFO] No CSS, PHP, HTML or VueJS files are found in the project. CSS analysis is skipped.
[INFO] Sensor CSS Rules [javascript] (done) | time=1ms
[INFO] Sensor C# Project Type Information [csharp]
[INFO] Sensor C# Project Type Information [csharp] (done) | time=0ms
[INFO] Sensor C# Analysis Log [csharp]
[INFO] Sensor C# Analysis Log [csharp] (done) | time=18ms
[INFO] Sensor C# Properties [csharp]
[INFO] Sensor C# Properties [csharp] (done) | time=0ms
[INFO] Sensor SurefireSensor [java]
[INFO] parsing [/Users/us-guest/Downloads/spring-boot-sonarquibe/target/surefire-reports]
[INFO] Sensor SurefireSensor [java] (done) | time=85ms
[INFO] Sensor HTML [web]
[INFO] Sensor HTML [web] (done) | time=2ms
[INFO] Sensor XML Sensor [xml]
[INFO] 1 source file to be analyzed
[INFO] 1/1 source file has been analyzed
[INFO] Sensor XML Sensor [xml] (done) | time=149ms
[INFO] Sensor Text Sensor [text]
[INFO] 4 source files to be analyzed
[INFO] 4/4 source files have been analyzed
[INFO] Sensor Text Sensor [text] (done) | time=5ms
[INFO] Sensor VB.NET Project Type Information [vbnet]
[INFO] Sensor VB.NET Project Type Information [vbnet] (done) | time=1ms
[INFO] Sensor VB.NET Analysis Log [vbnet]
[INFO] Sensor VB.NET Analysis Log [vbnet] (done) | time=17ms
[INFO] Sensor VB.NET Properties [vbnet]
[INFO] Sensor VB.NET Properties [vbnet] (done) | time=1ms
[INFO] ------------- Run sensors on project
[INFO] Sensor Analysis Warnings import [csharp]
[INFO] Sensor Analysis Warnings import [csharp] (done) | time=1ms
[INFO] Sensor Zero Coverage Sensor
[INFO] Sensor Zero Coverage Sensor (done) | time=0ms
[INFO] Sensor Java CPD Block Indexer
[INFO] Sensor Java CPD Block Indexer (done) | time=11ms
[INFO] SCM Publisher No SCM system was detected. You can use the 'sonar.scm.provider' property to explicitly specify it.
[INFO] CPD Executor 2 files had no CPD blocks
[INFO] CPD Executor Calculating CPD for 0 files
[INFO] CPD Executor CPD calculation finished (done) | time=0ms
[INFO] Analysis report generated in 52ms, dir size=132.0 kB
[INFO] Analysis report compressed in 24ms, zip size=22.2 kB
[INFO] Analysis report uploaded in 30ms
[INFO] ANALYSIS SUCCESSFUL, you can find the results at: http://localhost:9000/dashboard?id=spring-boot-sonarquibe
[INFO] Note that you will be able to access the updated dashboard once the server has processed the submitted analysis report
[INFO] More about the report processing at http://localhost:9000/api/ce/task?id=AYSOerN2q3cQPGRpKQoj
[INFO] Analysis total time: 5.107 s
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  13.422 s
[INFO] Finished at: 2022-11-19T00:01:56-06:00
[INFO] ------------------------------------------------------------------------
```
* in log it will tell the url where we can find the project result 
* http://localhost:9000/dashboard?id=spring-boot-sonarquibe
``` 
[INFO] ANALYSIS SUCCESSFUL, you can find the results at: http://localhost:9000/dashboard?id=spring-boot-sonarquibe
[INFO] Note that you will be able to access the updated dashboard once the server has processed the submitted analysis report
[INFO] More about the report processing at http://localhost:9000/api/ce/task?id=AYSOerN2q3cQPGRpKQoj
```
* we can find the analysis for the project with all the issued present 


* To stop sonarquibe server
  * $ sh sonar.sh stop
``` 
/usr/bin/java
Gracefully stopping SonarQube...
Stopped SonarQube.
```