# springboot-custom-validation

----
```
curl --location 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "adarsh",
  "email": "adarsh@kumar",
  "mobile": "+18877665544",
  "age": 50,
  "working": true,
  "aboutMe": "intelligent",
  "dateOfBirth": 13091983,
  "preference": [
    "java",
    "iot"
  ]
}'
```