# spring-boot-reactjs-monolithic

---

## STEP TO CREATE PROJECT STRUCTURE 
* create `spring-boot-reactjs-monolithic`
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

	<groupId>com.espark.adarsh</groupId>
	<artifactId>springboot-reacjs-monolithic</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>springboot-reacjs-monolithic</name>
	<description>Demo project for Spring Boot</description>

	<modules>
		<module>springboot-services</module>
		<module>reactjs-ui</module>
	</modules>

</project>
````

* create `springboot-services` maven module inside the `spring-boot-reactjs-monolithic` project
    * create a pom file with below tags
````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springboot-reacjs-monolithic</artifactId>
        <groupId>com.espark.adarsh</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>springboot-services</artifactId>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
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
                                    <directory>${project.parent.basedir}/reactjs-ui/build/</directory>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                </executions>
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
* create `reactjs-ui` maven module inside the `spring-boot-reactjs-monolithic` project
    * execute the below cmd inside the main project `spring-boot-ractjs-monolithic`
    * spring-boot-ractjs-monolithic $ create-react-app reactjs-ui
````
Installing packages. This might take a couple of minutes.
Installing react, react-dom, and react-scripts with cra-template...
````
    * `reactjs-ui/src/App.js` modify the code 
````
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          <h1>WELCOME TO THE ESPARK REACTJS</h1>
        </p>
      </header>
    </div>
  );
}
````
    * change the test case for the App.js 
      * reactjs-ui/src/App.test.js 
````
import { render, screen } from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/WELCOME TO THE ESPARK REACTJS/i);
  expect(linkElement).toBeInTheDocument();
});

````
    * To test the reactjs application running 
      * npm start 
      * http://localhost:3000
    * create a pom file with below tags 
````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springboot-reacjs-monolithic</artifactId>
        <groupId>com.espark.adarsh</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <version>0.0.1-SNAPSHOT</version>
    <artifactId>reactjs-ui</artifactId>

   <properties>
       <node.version>v14.18.0</node.version>
       <yarn.version>v1.12.1</yarn.version>
   </properties>
    <build>
        <plugins>
            <plugin>
            <groupId>com.github.eirslett</groupId>
            <artifactId>frontend-maven-plugin</artifactId>
            <version>1.12.1</version>
            <configuration>
                <workingDirectory>./</workingDirectory>
                <nodeVersion>v14.7.0</nodeVersion> <!--  $ node -v -->
                <npmVersion>6.14.8</npmVersion>  <!-- $ npm -v -->
                <nodeVersion>${node.version}</nodeVersion>
                <yarnVersion>${yarn.version}</yarnVersion>
            </configuration>
                <executions>
                    <execution>
                        <id>install node</id>
                        <goals>
                            <goal>install-node-and-yarn</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>yarn install</id>
                        <goals>
                            <goal>yarn</goal>
                        </goals>
                        <phase>generate-resources</phase>
                    </execution>
                    <execution>
                        <id>yarn test</id>
                        <goals>
                            <goal>yarn</goal>
                        </goals>
                        <phase>test</phase>
                        <configuration>
                            <arguments>test</arguments>
                            <environmentVariables>
                                <CI>true</CI>
                            </environmentVariables>
                        </configuration>
                    </execution>
                    <execution>
                        <id>yarn build</id>
                        <goals>
                            <goal>yarn</goal>
                        </goals>
                        <phase>compile</phase>
                        <configuration>
                            <arguments>build</arguments>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
````

### Test only the reactjs 
* spring-boot-reactjs-monolithic $ cd reactjs-ui 
* reactjs-ui $  npm start

### To Build the code 
* spring-boot-reactjs-monolithic $ mvn clean package 
* spring-boot-reactjs-monolithic $  java -jar ./springboot-services/target/springboot-services-0.0.1-SNAPSHOT.jar
* to access ui page `http://localhost:8080/`


### To access the web api 
* `http://localhost:8080/wish`