package com.espark.adarsh.respository;

import com.espark.adarsh.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository <Department,Long>{

    @Query("SELECT d FROM Department d WHERE d.parentDeptId = :parentDeptId")
    List<Department> findByParentDeptId(@Param("parentDeptId") Long parentDeptId);
}
