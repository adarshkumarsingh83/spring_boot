package com.espark.adarsh.repostiory.solr;

import com.espark.adarsh.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;


public interface SearchCategoryRepository extends SolrCrudRepository<Category, Long> {

    Page<Category> findByCatNameContaining(String catName, Pageable pageable);

    @Query(value = "catName:*?0*")
    Page<Category> findByCatName(String catName, Pageable pageable);

    @Query(name = "Category.findByCatNameOrCatPath")
    Page<Category> findByCatNameOrCatPathLike(String searchTerm, String catPath, Pageable pageable);

}