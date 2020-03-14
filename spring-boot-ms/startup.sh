#!/usr/bin/env bash
gradle build
gradle bootRun -p eureka-server &
gradle bootRun -p person-profile-ms &
gradle bootRun -p api-service &
gradle bootRun -p zuul-server &