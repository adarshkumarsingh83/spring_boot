package com.espark.adarsh.respository;

import com.espark.adarsh.entities.EmployeeExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeExperienceRepository extends JpaRepository<EmployeeExperience, Long> {

}
