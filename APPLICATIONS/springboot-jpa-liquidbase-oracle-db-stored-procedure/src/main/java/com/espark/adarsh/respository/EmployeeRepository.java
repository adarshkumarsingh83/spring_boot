package com.espark.adarsh.respository;

import com.espark.adarsh.entity.Employee;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Procedure(procedureName = "GET_EMPLOYEE_INFO", outputParameterName = "V_EMP_MSG")
    String getEmployeeStringByProcedure(@Param("V_EMP_NO") Long empNo);


    @Procedure(procedureName = "insertEmpProcedure", outputParameterName = "V_EMP_INSERT_MSG")
    String saveEmployeeByProcedure(
              @Param("V_EMPNAME") String empName
            , @Param("V_EMPEMAIL") String empEmail
    );

    @Procedure(procedureName = "updateEmpProcedure", outputParameterName = "V_EMP_UPDATE_MSG")
    String updateEmployeeByProcedure(@Param("V_EMPNO") Long empNo
            , @Param("V_EMPNAME") String empName
            , @Param("V_EMPEMAIL") String empEmail
    );

    @Procedure(procedureName = "deleteEmpProcedure", outputParameterName = "V_EMP_DEL_MSG")
    String deleteEmployeeByProcedure(@Param("V_EMP_NO") Long empNo);

}
