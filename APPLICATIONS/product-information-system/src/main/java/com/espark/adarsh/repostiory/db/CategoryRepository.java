package com.espark.adarsh.repostiory.db;

import com.espark.adarsh.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByCatName(String catName);

    //public Category findByParentCatId(Long parentCatId);

    @Query(value = "Select c From Category c where c.catPath LIKE :catPath%")
    List<Category> findByCatPathStartingWith(@Param("catPath") String catPath);

    @Query(value = "Select c From Category c where c.parentCatId = :parentCatId")
    List<Category> findByParentCatId(@Param("parentCatId") Long parentCatId);

}
