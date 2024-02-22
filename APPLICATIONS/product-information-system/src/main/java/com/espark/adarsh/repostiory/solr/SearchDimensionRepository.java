package com.espark.adarsh.repostiory.solr;

import com.espark.adarsh.entity.Dimension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;


public interface SearchDimensionRepository extends SolrCrudRepository<Dimension, Long> {

    Page<Dimension> findByDimNameContaining(String dimName, Pageable pageable);

}
