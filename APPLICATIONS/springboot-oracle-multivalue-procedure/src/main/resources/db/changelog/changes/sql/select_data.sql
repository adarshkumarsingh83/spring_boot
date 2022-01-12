CREATE OR REPLACE PROCEDURE
         SYSTEM.GET_EMPLOYEE_INFO( V_EMP_NAMES IN VARCHAR2, V_EMP_MSG OUT VARCHAR2 )
AS
 CURSOR EMP_CURSOR
   IS
     SELECT EMP_ID, FIRST_NAME, LAST_NAME, CAREER FROM SYSTEM.EMPLOYEE
                   WHERE SYSTEM.EMPLOYEE.FIRST_NAME
                       IN (
                              SELECT REGEXP_SUBSTR(V_EMP_NAMES,'[^,]+', 1, level) FROM DUAL
                                     CONNECT BY  REGEXP_SUBSTR(V_EMP_NAMES, '[^,]+', 1, level)
                       IS NOT NULL );
BEGIN
            FOR EMP IN EMP_CURSOR
            LOOP
                   V_EMP_MSG := V_EMP_MSG ||'  :  '|| EMP.EMP_ID ||' '|| EMP.FIRST_NAME ||' '|| EMP.LAST_NAME ||' '|| EMP.CAREER;
            END LOOP;
END GET_EMPLOYEE_INFO;

//