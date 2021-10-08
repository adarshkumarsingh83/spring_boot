package com.espark.adarsh.respository;

import com.espark.adarsh.entity.Employee;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Procedure(procedureName = "GET_EMPLOYEE_INFO", outputParameterName = "V_EMP_MSG")
    String getEmployeeData(@Param("V_EMP_NO") Long empNo);

    @Procedure(name = Employee.getEmpProcedure, outputParameterName = "V_SELECT_ERROR_MSG")
    Employee getEmployee(@Param("V_EMP_NO") Long empNo);
}
