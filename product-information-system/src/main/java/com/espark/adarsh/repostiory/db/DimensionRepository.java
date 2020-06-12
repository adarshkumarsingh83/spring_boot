package com.espark.adarsh.repostiory.db;

import com.espark.adarsh.entity.Dimension;
import com.espark.adarsh.entity.DimensionValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DimensionRepository
        extends CrudRepository<Dimension, Long> {

    @Query(value = "Select d From Dimension d where d.parentDimId = :parentDimId")
    List<Dimension> findByParentDimId(@Param("parentDimId") Long parentDimId);

    Dimension findByDimName(String dimName);

    //@Query(value = "Select d From Dimension d where c.level > level")
    List<Dimension> findByLevelGreaterThan(/*@Param("level") */Integer level);

}
