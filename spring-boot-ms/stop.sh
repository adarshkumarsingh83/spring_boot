#!/usr/bin/env bash
kill $(cat zuul-server/zuul-server.pid)
kill $(cat person-profile-ms/person-profile-ms.pid)
kill $(cat eureka-server/eureka-server.pid)
kill $(cat api-service/application.pid)