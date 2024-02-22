package com.espark.adarsh.repostiory.solr;

import com.espark.adarsh.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;


public interface SearchProductRepository extends SolrCrudRepository<Product, Long> {


    Page<Product> findByProductNameContaining(String searchTerm, Pageable pageable);

    @Query(value = "*:*", filters = {"productName:*?0*"})
    Page<Product> findByProductName(String searchTerm, Pageable pageable);


    @Query(name = "Product.findByProductNameOrCatId", defaultOperator = Operator.OR)
    Page<Product> findByProductNameOrCatId(String productName, String catId, Pageable pageable);


    @Query(name = "Product.findByCatId")
    Page<Product> findByCatIdOrDimension(Long catId, Pageable pageable);


    @Query(name = "Product.findDimension")
    Page<Product> findByDimension(String dimensions, Pageable pageable);

    @Query(name = "Product.findByCatIdOrDimension", defaultOperator = Operator.OR)
    Page<Product> findByCatIdOrDimension(Long catId, Long dimensions, Pageable pageable);

}