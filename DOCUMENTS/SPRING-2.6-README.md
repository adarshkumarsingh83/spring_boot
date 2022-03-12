
# spring boot 2.6 

### circular dependency is removed from 2.6.x 
* use below prop to contirue support for circular dependency 
* spring.main.allow-circular-references = true


## path pattern basd path matcing 
* AntPathMatcher earler used to match the api path 
* /api/{name}/{name} was working prior to 2.6 and last name value will be usedin @PathVariable 
* from 2.6 its invalid or use the below properties 
* spring.mvc.pathmatch.matching-strategy=ant_path_matcher

## actuator env infocontributor 
* management.endpoints.web.exposure.incluse=*
```
spring.applicationlaname = xxx
info.app.name=${spring.application.name}
info.app.api.version=xx
infor.api.docs=xxx
info.department.naem =xx 
info.department.email=xxx
```
* http://localhost:8080/actuator/info
* but from 2.6 onwards env infocontributor is disabled by default 
* use below prop to enabled and wire prop file properties to include in info endpint 
	* management.info.env.enabled=true 

## application startup 
```
SpringApplcation app = SpringApplication(Main.class);
app.setApplicationStartup(new BufferingApplictiaonStartup(1000))
or 
app.setApplicationStartup(new FlightRecorderApplictiaonStartup())
app.run(args);
```
* enable the actuator endpoints 
	* management.endpoints.web.exposure.incluse=*
* http://localhost:8080/actuator/startup 


## record and appliction config properties in jdk16 onwards 
* main class as well as record 
```
@ConstructorBinding
@ConfigurationProperties("xxx.xxx")
public record ApplicationProp(String name,String email){

}

@SpringBotoApplciatoin 
@EnabledConfigurationProperties(ApplicationPro.class)
public class Applicationmain{

}
```
* properties file 
```
xxx.xxx.name = xxx 
xxx.xxx.email = xxx 
```

### Cookie TBD 



### puuggable santitization 
* add cmd line argss 
```
--api.secret=zzz --api.version=1.0 --api.name=somethings
```
* enabled actuators 
	* management.endpoints.web.exposure.incluse=*
* Sanitizer.class in spring it will do this sanitzation for cmd lineproperites and evn variables 
	* look for DEFAULT_KEYS_TO_SANITIZE linkhasset which contains the list for sanitzation 
* localhost:8080/actuator/env 
	* look for cmdlineArgs in json reslponse for the cmd line propeties set ealirer 
* from 2.6 onwards we can write our own Sanitizer 
```
@Bean 
SanitizingFunction sanitizingFunction(){
	return data-> {
		return data.getPropertySource().getName().equals(CmmandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME)
		? data.withValue("XXXXXXX"):data;
	};
}
```
* localhost:8080/actuator/env 


### info endpoint with java runtime 
* enabled property 
	* management.info.java.enabled =ture 
	* now info api resposne will contains java runtime information also 
* http://localhost:8080/actuator/info

### Valdiation and error messages in api response 
* add dependency for 
	* springboot-starter-validation
* create a message.properties with key and value for error messages 
	* message_fr.properits for french 
	* spring.web.local=fr // will enabled the french propeties for messages 
* enable the properites 
	* server.erros.include-message=always
	* server.erros.include-binding-erros=always
* create a bean class and properites with javax.valdiation.constrains.XXX annotation 
	* @javax.valdiation.constraints.NotEmpty(message= "{key for the properties file }")
* create a controller and add the @Valid annotaiton where we are receving the bean as body of post or put 

### WebTestCliset 
* springwebflux dependency is required 
```
@WebMvcTest(ControllerName.class)
class ControllerTest{
	@Autowired
	WebTestclient webTestClient;

	@Test
	void testGet(){
		webTestclient()
		.get()
		.uri("api/url")
		.exchange()
		.expectedStatus().isOk()
		.expectedBody().jsonPath("$.jsonPropertiesName",Matchers.is("jsonProertiesValue"));
	}
}

```
