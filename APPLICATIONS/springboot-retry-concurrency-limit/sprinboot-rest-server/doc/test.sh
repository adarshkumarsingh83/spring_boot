#!/usr/bin/env bash
# Bash script: curl commands for Employee API
# Usage: chmod +x api-calls.sh && ./api-calls.sh

BASE_URL="http://localhost:8080"
# Example IDs
ID=1

echo "GET all employees"
curl -s -X GET "${BASE_URL}/employees?v=1.0" -H "Accept: application/json" | jq .


echo
echo "GET employee by id (${ID})"
curl -s -X GET "${BASE_URL}/employee/${ID}" -H "Accept: application/json" | jq .

echo
echo "CREATE new employee (POST)"
curl -s -X POST "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "usha singh",
    "email": "usha@singh.com"
  }' | jq .

echo
echo "UPDATE employee (${ID}) (PUT)"
curl -s -X PUT "${BASE_URL}/employee/${ID}" \
  -H "Content-Type: application/json" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d '{
      "name": "Adarsh kumar Updated",
      "email": "adarsh.kumar@updated.com"
    }' | jq .


echo "GET all employees"
curl -s -X GET "${BASE_URL}/employees?v=1.1" -H "Accept: application/json" | jq .
