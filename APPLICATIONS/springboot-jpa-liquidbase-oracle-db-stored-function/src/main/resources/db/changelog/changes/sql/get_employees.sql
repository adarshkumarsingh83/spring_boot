
 CREATE OR REPLACE FUNCTION getAllEmpFunction(V_SELECT_ERROR_MSG OUT VARCHAR) RETURN SYS_REFCURSOR
IS
   V_COUNT  NUMBER;
   V_EMP_ALL_DATA_CURSOR SYS_REFCURSOR;
BEGIN
       SELECT COUNT(*) INTO V_COUNT FROM EMPLOYEE;
       IF V_COUNT != 0 THEN
        OPEN V_EMP_ALL_DATA_CURSOR
          FOR SELECT EMP.EMP_NO,EMP.EMP_NAME,EMP.EMP_EMAIL FROM EMPLOYEE EMP;
       ELSE
          V_SELECT_ERROR_MSG :='NO EMPLOYEE FOUND';
       END IF;

       RETURN(V_EMP_ALL_DATA_CURSOR);
    CLOSE V_EMP_ALL_DATA_CURSOR;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
          BEGIN
          V_SELECT_ERROR_MSG :='NO EMPLOYEE FOUND';
          END;
        WHEN OTHERS THEN
          RAISE_APPLICATION_ERROR (-20001, 'EXCEPTION GENERATED :$:' || SQLERRM, TRUE) ;
END getAllEmpFunction;
//