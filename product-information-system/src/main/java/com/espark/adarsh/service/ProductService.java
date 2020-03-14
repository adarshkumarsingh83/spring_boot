package com.espark.adarsh.service;

import com.espark.adarsh.bean.ProductBean;
import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.exception.ResourceNotFound;

import java.util.List;

public interface ProductService {

    ProductBean saveProduct(ProductBean product);

    ProductBean updateProduct(Long productId, ProductBean product);

    ProductBean getProduct(Long productId) throws ResourceNotFound;

    ProductBean getProduct(String productName) throws ResourceNotFound;

    ProductBean deleteProduct(Long productId) throws ResourceNotFound;

    AbstractEntity getById(Long productId) throws ResourceNotFound;

    List<ProductBean> searchProduct(String searchTerm, int start, int max) throws ResourceNotFound;

}
