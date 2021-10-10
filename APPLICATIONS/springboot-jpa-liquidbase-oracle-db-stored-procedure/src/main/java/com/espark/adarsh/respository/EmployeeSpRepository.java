package com.espark.adarsh.respository;

import com.espark.adarsh.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeSpRepository {

    private final EntityManager entityManager;

    public List<Employee> getEmployeeByProcedure(Long empNo) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("getEmpProcedure", Employee.class);
        storedProcedureQuery.registerStoredProcedureParameter("V_EMP_NO", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("EMP_DATA_CURSOR", List.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.registerStoredProcedureParameter("V_SELECT_ERROR_MSG", String.class, ParameterMode.OUT);
        storedProcedureQuery.setParameter("V_EMP_NO", empNo);
        storedProcedureQuery.execute();
       // storedProcedureQuery.getOutputParameterValue("EMP_DATA_CURSOR");
       // storedProcedureQuery.getOutputParameterValue("V_SELECT_ERROR_MSG");
        return storedProcedureQuery.getResultList();
    }

    public List<Employee> getEmployeesByProcedure() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("getAllEmpProcedure", Employee.class);
        storedProcedureQuery.registerStoredProcedureParameter("EMP_ALL_DATA_CURSOR", List.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.registerStoredProcedureParameter("V_SELECT_ERROR_MSG", String.class, ParameterMode.OUT);
        storedProcedureQuery.execute();
        // storedProcedureQuery.getOutputParameterValue("EMP_DATA_CURSOR");
        // storedProcedureQuery.getOutputParameterValue("V_SELECT_ERROR_MSG");
        return storedProcedureQuery.getResultList();
    }
}
