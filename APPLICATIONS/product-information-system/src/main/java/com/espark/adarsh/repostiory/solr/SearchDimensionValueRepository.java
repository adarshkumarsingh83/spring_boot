package com.espark.adarsh.repostiory.solr;

import com.espark.adarsh.entity.DimensionValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;


public interface SearchDimensionValueRepository extends SolrCrudRepository<DimensionValue, Long> {

    Page<DimensionValue> findByDimId(Long dimId, Pageable pageable);

    Page<DimensionValue> findByDimValNameContaining(String dimValName, Pageable pageable);
}
