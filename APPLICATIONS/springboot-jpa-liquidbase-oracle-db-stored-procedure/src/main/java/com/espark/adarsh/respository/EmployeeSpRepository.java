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

    public List<Employee> execStoredProdDirectly(Long empNo) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("getEmpProcedure", Employee.class);
        storedProcedureQuery.registerStoredProcedureParameter("V_EMP_NO", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("V_SELECT_ERROR_MSG", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("EMP_DATA_CURSOR", List.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter("V_EMP_NO", empNo);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getResultList();
    }
}
