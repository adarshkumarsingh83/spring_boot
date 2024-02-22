package com.espark.adarsh.respository;

import com.espark.adarsh.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Procedure(procedureName = "SYSTEM.GET_EMPLOYEE_INFO",outputParameterName = "V_EMP_MSG")
    String getEmployeeData(@Param("V_EMP_NAMES") String empNames);
}
