package com.espark.adarsh.espark.respository;

import com.espark.adarsh.espark.entites.HibernateSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HibernateSequenceRepository extends JpaRepository<HibernateSequence, Void>, JpaSpecificationExecutor<HibernateSequence> {

}