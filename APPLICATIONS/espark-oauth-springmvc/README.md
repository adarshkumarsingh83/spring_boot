# espark-oauth-springmvc



# To download
* https://www.keycloak.org/
* download keycloak-xx.x.x.tar.gz for mac 

# To start the application server
* keycloak-22.0.5/bin $ sh kc.sh start-dev --http-port=8181

# homepage url
* http://localhost:8181/

# create admin credentials by login into the
* Administration Console
* provide the adminname/adminpwd to create admin and login into the admin

# create Realm role
* espark-oauth


# To create client
```
select client -> create client

clientid : espark-oauth-springmvc
name :espark-oauth-springmvc
next->
client authentication :on
authenticaton flow : std flow
next ->
Valid redirect URI : http://localhost:8080/login/oauth2/code/espark-oauth-springmvc
save ->
```
# create user
* select user -> provide name and -> credentials -> provide pwd 

# config in application 
```

server.port=8080

# client -> setings -> clientid 
spring.security.oauth2.client.registration.espark-oauth-springmvc.client-id=espark-oauth-springmvc

# client -> credentials -> client secret 
spring.security.oauth2.client.registration.espark-oauth-springmvc.client-secret=qUIbRuiDaTpazJYZlufnCWWUmBKQH5sm

spring.security.oauth2.client.registration.espark-oauth-springmvc.scope=openid, profile, roles
spring.security.oauth2.client.registration.espark-oauth-springmvc.authorization-grant-type=authorization_code

# client -> valid redirect url 
spring.security.oauth2.client.registration.espark-oauth-springmvc.redirect-uri=http://localhost:8080/login/oauth2/code/espark-oauth-springmvc

# realm setting -> endpoints -> openid endpoint config -> "issuer": "http://localhost:8181/realms/espark-oauth",
spring.security.oauth2.client.provider.espark-oauth-springmvc.issuer-uri=http://localhost:8181/realms/espark-oauth

```
