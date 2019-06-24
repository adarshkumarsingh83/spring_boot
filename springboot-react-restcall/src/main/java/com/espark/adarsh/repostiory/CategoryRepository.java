package com.espark.adarsh.repostiory;

import com.espark.adarsh.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    public Category findByCatName(String catName);

    @Query(value = "Select c From Category c where c.catPath LIKE :catPath%")
    List<Category> findByCatPathStartingWith(@Param("catPath") String catPath);

}
