package com.espark.adarsh.respository;

import com.espark.adarsh.entities.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {

}
