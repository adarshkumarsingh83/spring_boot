package com.espark.adarsh.respository;

import com.espark.adarsh.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.managerId = :managerId")
    List<Employee> findByManagerId(@Param("managerId") Long managerId);

    @Query("SELECT e FROM Employee e WHERE e.deptId = :deptId")
    List<Employee> findByDepartmentId(@Param("deptId") Long deptId);

    @Query("SELECT e FROM Employee e WHERE e.deptId = :deptId AND e.designation LIKE CONCAT('%', :designation, '%')")
    List<Employee> findManagerBytDeptId(@Param("deptId") Long deptId, @Param("designation") String designation);
}
