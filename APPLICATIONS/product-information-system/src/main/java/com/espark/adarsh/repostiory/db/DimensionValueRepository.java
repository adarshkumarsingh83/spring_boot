package com.espark.adarsh.repostiory.db;

import com.espark.adarsh.entity.DimensionValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DimensionValueRepository
        extends CrudRepository<DimensionValue,Long> {

    @Query(value = "Select d From DimensionValue d where d.dimId = :dimId")
    List<DimensionValue> findByDimId(@Param("dimId")Long dimId);
}
