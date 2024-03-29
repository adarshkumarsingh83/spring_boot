CREATE OR REPLACE FUNCTION getEmpFunction(V_EMP_NO IN EMPLOYEE.EMP_NO%TYPE ,V_SELECT_ERROR_MSG OUT VARCHAR) RETURN SYS_REFCURSOR
IS
   V_COUNT  NUMBER;
   V_EMP_DATA_CURSOR SYS_REFCURSOR;
  BEGIN

    SELECT COUNT(*) INTO V_COUNT FROM EMPLOYEE WHERE EMP_NO = V_EMP_NO;

    IF V_COUNT != 0 THEN
			OPEN V_EMP_DATA_CURSOR
			FOR SELECT EMP.EMP_NO,EMP.EMP_NAME,EMP.EMP_EMAIL FROM EMPLOYEE EMP WHERE EMP.EMP_NO = V_EMP_NO;
	ELSE
			  V_SELECT_ERROR_MSG :='NO EMPLOYEE FOUND FOR EMP_NO '||V_EMP_NO;
    END IF;

    RETURN(V_EMP_DATA_CURSOR);

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
			BEGIN
			 V_SELECT_ERROR_MSG:='NO EMPLOYEE FOUND';
            END;
        WHEN TOO_MANY_ROWS THEN
          BEGIN
            RAISE_APPLICATION_ERROR(-20612,'MORE THEN ONE EMPLOYEE FOUND FOR EMP_NO '||V_EMP_NO);
          END;
        WHEN OTHERS THEN
		   RAISE_APPLICATION_ERROR(-20001, V_EMP_NO || ':$:' || SQLERRM, TRUE) ;
END getEmpFunction;

//