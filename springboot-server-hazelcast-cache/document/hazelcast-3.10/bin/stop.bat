@echo off

SETLOCAL

set "CLASSPATH=%~dp0..\lib\hazelcast-all-3.10.jar"

taskkill /F /FI "WINDOWTITLE eq hazelcast %CLASSPATH%"
