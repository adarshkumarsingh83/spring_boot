#!/bin/bash

index=0
while [ $index -lt 50 ] 
do 
  echo '\t'
  echo $index
    curl  http://localhost:8080/api/wish; 
    sleep 1; 
  index=`expr  $index + 1`
done
