CREATE OR REPLACE FUNCTION GET_EMPLOYEE_INFO_FUNCTION
( V_EMP_NO IN EMPLOYEE.EMP_NO%TYPE, V_EMP_MSG OUT VARCHAR )
AS
BEGIN
   SELECT EMP_NO ||' '|| EMP_NAME ||' '|| EMP_EMAIL  INTO V_EMP_MSG
   FROM EMPLOYEE WHERE EMPLOYEE.EMP_NO = V_EMP_NO;
END;
//

