package com.espark.adarsh.repostiory.db;

import com.espark.adarsh.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
        extends JpaRepository<Product,Long> {

        public Product findByProductName(String productName);

        public Product findByCatId(Long catId);

}
