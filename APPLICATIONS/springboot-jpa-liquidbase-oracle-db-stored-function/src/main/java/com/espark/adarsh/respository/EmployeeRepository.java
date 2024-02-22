package com.espark.adarsh.respository;

import com.espark.adarsh.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Procedure(procedureName = "GET_EMPLOYEE_INFO_FUNCTION", outputParameterName = "V_EMP_MSG")
    String getEmployeeData(@Param("V_EMP_NO") Long empNo);


    @Procedure(procedureName = "insertEmpFunction")
    String saveEmployeeByFunction(
            @Param("V_EMPNAME") String empName
            , @Param("V_EMPEMAIL") String empEmail
    );

    @Procedure(procedureName = "updateEmpFunction")
    String updateEmployeeByFunction(@Param("V_EMPNO") Long empNo
            , @Param("V_EMPNAME") String empName
            , @Param("V_EMPEMAIL") String empEmail
    );

    @Procedure(procedureName = "deleteEmpFunction")
    String deleteEmployeeByFunction(@Param("V_EMP_NO") Long empNo);

}
