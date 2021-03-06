
# AutoConfiguration

---

```
AutoConfiguration class looks like any regular Spring @Configuration class, 
but is enriched with @Conditional annotations that will activate or not a bean, 
or a set of them, only under certain circumstances
```

* @ConditionalOnClass
* @ConditionalOnMissingBean
* @ConditionalOnBean
* @ConditionalOnJava
* @ConditionalOnJndi
* @ConditionalOnMissingClass
* @ConditionalOnExpression
* @ConditionalOnNotWebApplication
* @ConditionalOnWebApplication
* @ConditionalOnProperty
* @ConditionalOnResource
* @ConditionalOnSingleCandidate


### Register your own Autoconfiguration
```
In order to use an Autoconfiguration class, Spring needs to know where to look for it.
 This step is done using the standard META-INF/spring.factories file,
 adding the full name of the configuration class under the entry 
 org.springframework.boot.autoconfigure.EnableAutoConfiguration.
 ```
 
### application-name/src/main/resources/META-INF/spring.factories
```
 org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 com.espark.adarsh.autoconfigure.EsparkAutoConfiguration

```