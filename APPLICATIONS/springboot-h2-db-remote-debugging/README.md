# springboot-h2-db-docker-remote-debugging

---

### to build the application
* $ mvn clean package

### To Run the application 
* $ mvn spring-boot:run

### log into the homepage of db
* http://localhost:8080/h2-console
```
username, pwd , dburl and db driver class is mentioned in application.properties file
```

### To Test the api 
* curl -X GET http://localhost:8080/employees
````
[
  {
    "id": 1,
    "firstName": "adarsh",
    "lastName": "kumar",
    "career": "It"
  },
  {
    "id": 2,
    "firstName": "radha",
    "lastName": "singh",
    "career": "IT"
  },
  {
    "id": 3,
    "firstName": "sonu",
    "lastName": "singh",
    "career": "IT"
  },
  {
    "id": 4,
    "firstName": "amit",
    "lastName": "kumar",
    "career": "Finance"
  }
]
````

## Create a `application-debugging.sh` with below content
````
# application-debugging.sh
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8090,suspend=n -jar springboot-h2-db-docker-remote-debugging.jar
````

### Start Running Application in Debug mode using `application-debugging.sh`
* $ sh application-debugging.sh
````
Listening for transport dt_socket at address: 8090

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.6.RELEASE)

2022-02-25 22:16:22.720  INFO 15948 --- [           main] com.espark.adarsh.ApplicationMain        : Starting ApplicationMain v0.0.1-SNAPSHOT on USMB113823.attlocal.net with PID 15948 (/Users/us-guest/Desktop/springboot-h2-db-remote-debugging/target/springboot-h2-db-remote-debugging.jar started by us-guest in /Users/us-guest/Desktop/springboot-h2-db-remote-debugging)
2022-02-25 22:16:22.722  INFO 15948 --- [           main] com.espark.adarsh.ApplicationMain        : No active profile set, falling back to default profiles: default
2022-02-25 22:16:23.224  INFO 15948 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2022-02-25 22:16:23.282  INFO 15948 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 50ms. Found 1 JPA repository interfaces.
2022-02-25 22:16:23.815  INFO 15948 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-02-25 22:16:23.826  INFO 15948 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-02-25 22:16:23.826  INFO 15948 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.33]
2022-02-25 22:16:23.885  INFO 15948 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-02-25 22:16:23.886  INFO 15948 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1123 ms
2022-02-25 22:16:23.931  INFO 15948 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-02-25 22:16:24.120  INFO 15948 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2022-02-25 22:16:24.125  INFO 15948 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
2022-02-25 22:16:24.255  INFO 15948 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2022-02-25 22:16:24.340  INFO 15948 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.12.Final
2022-02-25 22:16:24.508  INFO 15948 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.0.Final}
2022-02-25 22:16:24.640  INFO 15948 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2022-02-25 22:16:25.250  INFO 15948 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2022-02-25 22:16:25.258  INFO 15948 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2022-02-25 22:16:25.561  WARN 15948 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2022-02-25 22:16:25.768  INFO 15948 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2022-02-25 22:16:26.154  INFO 15948 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-02-25 22:16:26.156  INFO 15948 --- [           main] com.espark.adarsh.ApplicationMain        : Started ApplicationMain in 3.808 seconds (JVM running for 4.534)
````

### To Add Remote Debugging in Intellj ide 
* Go to Edit Configuration of Run 
    * Click on + button in left corner 
      * Search for the Remote JVM Debugging 
        * provide the name = xxx 
        * Debugger mode = attach to remote jvm 
        * host = host name
        * port =  which we have given for remote debugging and save it 
* start the application in debugging 
````
Connected to the target VM, address: 'localhost:8090', transport: 'socket'
````
* add the debugging point in the code and hit the api for debugging 