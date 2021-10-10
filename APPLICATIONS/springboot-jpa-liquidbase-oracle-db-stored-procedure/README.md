# SPRING BOOT JPA ORACLE DB LIQUID BASE STORED PROCEDURE

---

### To Build Code 
* mvn clean package

### TO CHECK DATA INTO THE DB
* SELECT * FROM SYSTEM.EMPLOYEE;
* SELECT * FROM SYSTEM.DATABASECHANGELOG;

## TO CHECK SQL SCRPT WORKED OR NOT 
* SELECT * FROM SYSTEM.DATABASECHANGELOG;
* TRUNCATE TABLE SYSTEM.DATABASECHANGELOG;

## TO DROP THE TABLES IN DB 
* DROP PROCEDURE SYSTEM.getEmpProcedure;
* DROP PROCEDURE SYSTEM.getAllEmpProcedure;
* DROP PROCEDURE SYSTEM.insertEmpProcedure;
* DROP PROCEDURE SYSTEM.updateEmpProcedure;
* DROP PROCEDURE SYSTEM.deleteEmpProcedure;
* DROP TABLE SYSTEM.EMPLOYEE;
* DROP TABLE SYSTEM.DATABASECHANGELOG;
* DROP TABLE SYSTEM.DATABASECHANGELOGLOCK;


## To Test the getEmpProcedure 
``` 
SET SERVEROUTPUT ON;
 DECLARE
   V_EMP_DATA_CURSOR SYS_REFCURSOR;
   V_EMPNO EMPLOYEE.EMP_NO%TYPE;
   V_EMPNAME EMPLOYEE.EMP_NAME%TYPE;
   V_EMPEMAIL EMPLOYEE.EMP_EMAIL%TYPE;
   V_SELECT_ERROR_MSG VARCHAR(50);
 BEGIN
      getEmpProcedure(&EMPNO,V_EMP_DATA_CURSOR,V_SELECT_ERROR_MSG);
	    IF V_SELECT_ERROR_MSG IS NULL THEN 
			 LOOP
				 FETCH V_EMP_DATA_CURSOR INTO V_EMPNO,V_EMPNAME,V_EMPEMAIL;
				 EXIT WHEN V_EMP_DATA_CURSOR%NOTFOUND;
				 DBMS_OUTPUT.PUT_LINE('EMPLOYEE DATA '||V_EMPNO||','||V_EMPNAME||','||V_EMPEMAIL);
			 END LOOP;
			 CLOSE V_EMP_DATA_CURSOR;
		ELSE
            DBMS_OUTPUT.PUT_LINE(V_SELECT_ERROR_MSG);
        END IF;	
 END;
 /
```

### TO TEST getAllEmpProcedure 
````
 SET SERVEROUTPUT ON;
 DECLARE
   V_EMP_DATA_CURSOR SYS_REFCURSOR;
   V_EMPNO EMPLOYEE.EMP_NO%TYPE;
   V_EMPNAME EMPLOYEE.EMP_NAME%TYPE;
   V_EMPEMAIL EMPLOYEE.EMP_EMAIL%TYPE;
   V_SELECT_ERROR_MSG VARCHAR(50);
 BEGIN
    getAllEmpProcedure(V_EMP_DATA_CURSOR,V_SELECT_ERROR_MSG);
	IF V_SELECT_ERROR_MSG IS NULL THEN
			 LOOP
				 FETCH V_EMP_DATA_CURSOR INTO V_EMPNO,V_EMPNAME,V_EMPEMAIL;
				 EXIT WHEN V_EMP_DATA_CURSOR%NOTFOUND;
				 DBMS_OUTPUT.PUT_LINE('EMPLOYEE DATA '||V_EMPNO||','||V_EMPNAME||','||V_EMPEMAIL);
			 END LOOP;
			 CLOSE V_EMP_DATA_CURSOR;
	ELSE
		DBMS_OUTPUT.PUT_LINE(V_SELECT_ERROR_MSG);
    END IF;	
 END;
 /
````

### TO TEST insertEmpProcedure
``` 
SET SERVEROUTPUT ON;
DECLARE
   DATA VARCHAR2(500) ;
BEGIN
insertEmpProcedure('&EMPNAME','&EMPEMAIL' ,DATA);
DBMS_OUTPUT.PUT_LINE(DATA);
END;
/
```

### TO TEST THE updateEmpProcedure
``` 
SET SERVEROUTPUT ON;
DECLARE
   DATA VARCHAR2(500) ;
BEGIN
updateEmpProcedure(&EMPNO,'&EMPNAME','&EMPEMAIL' ,DATA);
DBMS_OUTPUT.PUT_LINE(DATA);
END;
/
```

### TO TEST THE deleteEmpProcedure
``` 
SET SERVEROUTPUT ON;
DECLARE
   V_EMP_DEL_MSG VARCHAR2(500) ;
BEGIN
	deleteEmpProcedure(&EMPNO,V_EMP_DEL_MSG);
	DBMS_OUTPUT.PUT_LINE(V_EMP_DEL_MSG);
END;
/
```

---
# ORACLE DB DOCKER IMAGE AND DEPLOYMENT 
## Clone the git hub from oracle
*  git clone git@github.com:oracle/docker-images.git

## move to the docker file directory
* $ cd docker-images/OracleDatabase/SingleInstance/dockerfiles

# list the supported version
* $ ls
```
11.2.0.2		12.2.0.1		18.4.0			21.3.0
12.1.0.2		18.3.0			19.3.0			buildContainerImage.sh
```

## url to download the version of oracle zip
* https://www.oracle.com/database/technologies/xe18c-downloads.html
* copy into the same directory of the version in ls cmd

## Build the docker image
* $  sh buildContainerImage.sh -v 18.4.0 -x

``` 
Oracle Database container image for 'xe' version 18.4.0 is ready to be extended: 
    
    --> oracle/database:18.4.0-xe

  Build completed in 510 seconds.
```

## db client opensource
* https://dbeaver.io/download/

##  launch the db
* $ docker run --name OracleXE \
  --shm-size=1g \
  -p 1521:1521 \
  -p 8081:8080 \
  -e ORACLE_PWD=12345 \
  -v oracle-data:/u01/app/oracle/oradata \
  oracle/database:18.4.0-xe

``` 
ORACLE PASSWORD FOR SYS AND SYSTEM: 12345
Specify a password to be used for database accounts. Oracle recommends that the password entered should be at least 8 characters in length, contain at least 1 uppercase character, 1 lower case character and 1 digit [0-9]. Note that the same password will be used for SYS, SYSTEM and PDBADMIN accounts:
Confirm the password:
Configuring Oracle Listener.
Listener configuration succeeded.
Configuring Oracle Database XE.
Enter SYS user password: 
******
Enter SYSTEM user password: 
*****
Enter PDBADMIN User Password: 
*****
Prepare for db operation
7% complete
Copying database files
29% complete
Creating and starting Oracle instance
Completing Database Creation
47% complete
50% complete
Creating Pluggable Databases
54% complete
71% complete
Executing Post Configuration Actions
93% complete
Running Custom Scripts
100% complete
Database creation complete. For details check the logfiles at:
 /opt/oracle/cfgtoollogs/dbca/XE.
Database Information:
Global Database Name:XE
System Identifier(SID):XE
Look at the log file "/opt/oracle/cfgtoollogs/dbca/XE/XE.log" for further details.

Connect to Oracle Database using one of the connect strings:
     Pluggable database: 2f7354fc4ee1/XEPDB1
     Multitenant container database: 2f7354fc4ee1
Use https://localhost:5500/em to access Oracle Enterprise Manager for Oracle Database XE
The Oracle base remains unchanged with value /opt/oracle
#########################
DATABASE IS READY TO USE!
#########################
The following output is now a tail of the alert.log:
2021-10-05T23:03:43.054971+00:00
XEPDB1(3):Resize operation completed for file# 10, old size 358400K, new size 368640K
2021-10-05T23:03:56.975470+00:00
XEPDB1(3):CREATE SMALLFILE TABLESPACE "USERS" LOGGING  DATAFILE  '/opt/oracle/oradata/XE/XEPDB1/users01.dbf' SIZE 5M REUSE AUTOEXTEND ON NEXT  1280K MAXSIZE UNLIMITED  EXTENT MANAGEMENT LOCAL  SEGMENT SPACE MANAGEMENT  AUTO
XEPDB1(3):Completed: CREATE SMALLFILE TABLESPACE "USERS" LOGGING  DATAFILE  '/opt/oracle/oradata/XE/XEPDB1/users01.dbf' SIZE 5M REUSE AUTOEXTEND ON NEXT  1280K MAXSIZE UNLIMITED  EXTENT MANAGEMENT LOCAL  SEGMENT SPACE MANAGEMENT  AUTO
XEPDB1(3):ALTER DATABASE DEFAULT TABLESPACE "USERS"
XEPDB1(3):Completed: ALTER DATABASE DEFAULT TABLESPACE "USERS"
2021-10-05T23:04:03.318085+00:00
ALTER PLUGGABLE DATABASE XEPDB1 SAVE STATE
Completed: ALTER PLUGGABLE DATABASE XEPDB1 SAVE STATE

```

## Connect to db
```
Host: localhost
Port: 1521
Database: XE
Use SID
User name: SYS
Role: SYSDBA
Password: 12345
```

## Clean up
* docker ps -a
* docker rm [OPTIONS] CONTAINER [CONTAINER...]
* docker rm -f [CONTAINER_ID]
* docker rm -f 2f7354fc4ee1
* docker rm -f [CONTAINER_NAME] OracleXE
* docker rm -f  OracleXE

[HELP FROM BLOG](https://www.codesanook.com/setup-oracle-xe-database-on-docker-container-and-connect-with-dbeaver)

## Api Testing 
### To Fetch the Employee 
* $ curl -X GET http://localhost:8080/employee/1
``` 
{"empNo":1,"empName":"adarsh","empEmail":"adarsh@kumar"}
```

### To Creating the Employee
* $ curl -X POST http://localhost:8080/employee -H 'Content-Type: application/json' -d '{"empName":"adi","empEmail":"adi@kumar"}'
``` 
  {"empNo":5,"empName":"adi","empEmail":"adi@kumar"}
```

### To Create employee using procedure 
* $ curl -X POST http://localhost:8080/employee/pro -H 'Content-Type: application/json' -d '{"empName":"adi","empEmail":"adi@kumar"}'
```  
EMPLOYEE INSERT SUCCESSFUL 1 EMPLOYEE DATA 5 adi adi@kumar
```

### To Update employee 
* $ curl -X PUT http://localhost:8080/employee/5 -H 'Content-Type: application/json' -d '{"empNo":5,"empName":"adik","empEmail":"adi@kumar"}'
``` 
{"empNo":5,"empName":"adik","empEmail":"adi@kumar"}
```

### To Update employee using procedure
* $ curl -X PUT http://localhost:8080/employee/pro/5 -H 'Content-Type: application/json' -d '{"empNo":5,"empName":"adiii","empEmail":"adi@kumar"}'
``` 
EMPLOYEE UPDATED SUCCESSFUL 1UPDATED EMPLOYEE DATA5 adiii adi@kumar
```

## To Delete employee 
* curl -X DELETE http://localhost:8080/employee/5
``` 
{"empNo":5,"empName":"adik","empEmail":"adi@kumar"}
```

## To Delete employee using procedure
* curl -X DELETE http://localhost:8080/employee/pro/5
```
EMPLOYEE DELETED SUCCESSFUL 1 EMPLOYEE DELETED 5 adiii adi@kumar
```

## To Fetch employee using procedure 
* curl -X GET http://localhost:8080/employee/pro/1


## To Fetch employees using procedure 
* $ curl -X GET http://localhost:8080/employees/pro
``` 
[{"empNo":1,"empName":"adarsh","empEmail":"adarsh@kumar"},{"empNo":2,"empName":"radha","empEmail":"radha@singh"},{"empNo":3,"empName":"sonu","empEmail":"sonu@singh"},{"empNo":4,"empName":"amit","empEmail":"amit@kumar"}]
```