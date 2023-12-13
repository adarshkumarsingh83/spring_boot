# springboot-dynamic-payload-response

---

## to build 
* mvn clean packgqe  

## to run 
* mvn spring-boot:run 


## To access the api url 
* api without dynamic payload
  * http://localhost:8080/api/response
```
  {
  "employeeBean": {
    "empId": "01",
    "firstName": "firstName",
    "lastName": "lastName"
  },
  "deptBean": {
    "deptId": null,
    "deptName": null
  },
  "addressBean": {
    "addressId": null,
    "street": null,
    "city": null
  }
 }
```
* api with dynamic payload  
  * http://localhost:8080/api/custom/response
```
{
"employeeBean": {
  "empId": "01",
  "firstName": "firstName",
  "lastName": "lastName"
},
"deptBean": {},
"addressBean": {}
}
```  


