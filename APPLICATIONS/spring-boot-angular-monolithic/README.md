# spring-boot-angular-monolithic 
---

## STEP TO CREATE PROJECT STRUCTURE 
* create `spring-boot-angular-monolithic`
  * create a pom file with below tags 
````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
						https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<packaging>pom</packaging>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<groupId>com.esprak.adarsh</groupId>
	<artifactId>spring-boot-angular-monolithic</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-boot-angular-monolithic</name>
	<description>Demo project for Spring Boot</description>

	<modules>
		<module>springboot-services</module>
		<module>angularjs-ui</module>
	</modules>

</project>

````
* create `springboot-services` maven module inside the `spring-boot-angular-monolithic` project
  * create a pom file with below tags
````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-boot-angular-monolithic</artifactId>
        <groupId>com.esprak.adarsh</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <modelVersion>4.0.0</modelVersion>
    <artifactId>springboot-services</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <properties>
        <java.version>1.8</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>com.esprak.adarsh</groupId>
            <artifactId>angularjs-ui</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-resources-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-resources</id>
                        <phase>validate</phase>
                        <goals><goal>copy-resources</goal></goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/classes/resources/</outputDirectory>
                            <resources>
                                <resource>
                                    <directory>${project.parent.basedir}/angularjs-ui/dist/adarsh/</directory>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
````
* create `angularjs-ui` maven module inside the `spring-boot-angular-monolithic` project
    * create a pom file with below tags 
````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-boot-angular-monolithic</artifactId>
        <groupId>com.esprak.adarsh</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>angularjs-ui</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <build>
        <resources>
            <resource>
                <directory>./dist/adarsh</directory>
                <targetPath>static</targetPath>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>com.github.eirslett</groupId>
                <artifactId>frontend-maven-plugin</artifactId>
                <version>1.7.6</version>
                <configuration>
                    <workingDirectory>./</workingDirectory>
                    <nodeVersion>v10.16.0</nodeVersion>
                    <npmVersion>6.10.2</npmVersion>
                </configuration>
                <executions>
                    <execution>
                        <id>install node and npm</id>
                        <goals>
                            <goal>install-node-and-npm</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>npm install</id>
                        <goals>
                            <goal>npm</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>npm run build</id>
                        <goals>
                            <goal>npm</goal>
                        </goals>
                        <configuration>
                            <arguments>run build</arguments>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>      
````
    * execute the below cmd inside the main project `spring-boot-angular-monolithic`
    * cd spring-boot-angular-monolithic
    * :spring-boot-angular-monolithic $ ng new angularjs-ui
    * ? Would you like to add Angular routing? Yes
    * ? Which stylesheet format would you like to use ?
      * select SCSS option 
````
       CSS
       SCSS   [ https://sass-lang.com/documentation/syntax#scss                ]   <- selet scss 
       Sass   [ https://sass-lang.com/documentation/syntax#the-indented-syntax ]
       Less   [ http://lesscss.org                                             ]
       Stylus [ http://stylus-lang.com                                         ]
````
    * import the bootstarp in the project 
      *  npm i bootstrap --save
    * import the boot srap in angularjs application 
      * spring-boot-angular-monolithic/angularjs-ui/src/app/app.component.html which is starting point for the angular  
      * NOTE: clean the file and only leave the <'router-outlet></'router-outlet>
```
<div>
  <h1 class="display-1"> welcome to espark angularjs monotlithic application </h1>
</div>
<router-outlet></router-outlet>
```
      *  spring-boot-angular-monolithic/angularjs-ui/src/style.cass  bootstrap has to be added into the 
```
   @import '~bootstrap/dist/css/bootstrap.min.css';
```

### Test the angular 
* cd angularjs-ui 
* angularjs-ui $ ng serve
* to access angularjs page `http://localhost:4200/`
### To Build the code
* spring-boot-angular-monolithic $ mvn clean package 
* spring-boot-angular-monolithic $ java -jar ./springboot-services/target/springboot-services-0.0.1-SNAPSHOT.jar
* to access ui page `http://localhost:8080/`


### To access the web api
* `http://localhost:8080/wish`