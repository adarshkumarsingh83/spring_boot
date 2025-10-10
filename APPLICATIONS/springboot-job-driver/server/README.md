# job server 

---
### To Start job 
* data-load
* data-cleanup
* submit-data
* resubmit-data
```
curl --location --request POST 'http://localhost:8080/job/start' \
--header 'Content-Type: application/json' \
--data-raw '{
    "data": {
      "jobName":"data-load"
     },
     "message" : "request"
}'
```
* response 
```
{
    "data": {
        "jobId": "8d5d450a-70b3-4a90-94d2-e989275e6cba",
        "jobName": "data-load",
        "startedBy": null,
        "startedOn": "2025-06-04T20:51:17.206849",
        "status": "EXECUTING",
        "expectedCompletion": "2025-06-04T20:56:17.206954",
        "message": "job is executing "
    },
    "message": "job is starting "
}
```

### To Check Status 
```
curl --location --request GET 'http://localhost:8080/job/status/8d5d450a-70b3-4a90-94d2-e989275e6cba'
```
* response 
````
{
  "data": {
    "jobId": "8d5d450a-70b3-4a90-94d2-e989275e6cba",
    "jobName": "data-load",
    "startedBy": null,
    "startedOn": "2025-06-04T20:51:17.206849",
    "status": "EXECUTING",
    "expectedCompletion": "2025-06-04T20:56:17.206954",
    "message": "job is executing "
  },
  "message": "job is executing "
}
````

````
{
    "data": {
        "jobId": "8d5d450a-70b3-4a90-94d2-e989275e6cba",
        "jobName": "data-load",
        "startedBy": null,
        "startedOn": "2025-06-04T20:51:17.206849",
        "status": "COMPLETED",
        "expectedCompletion": "2025-06-04T20:56:17.206954",
        "message": "job is completed "
    },
    "message": "job is completed "
}
```

