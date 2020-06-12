package com.espark.adarsh.service;

import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.entity.Category;
import com.espark.adarsh.exception.ResourceNotFound;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService extends AbstractService {

    CategoryBean saveCategory(CategoryBean category);

    Category findByCatName(String catName) throws ResourceNotFound;

    List<CategoryBean> getCategory() throws ResourceNotFound;

    CategoryBean updateCategory(Long categoryId, CategoryBean category) throws ResourceNotFound;

    CategoryBean getCategory(Long categoryId) throws ResourceNotFound;

    CategoryBean deleteCategory(Long CategoryId) throws ResourceNotFound;

    List<CategoryBean> searchCategory(String searchTerm, int start, int max) throws ResourceNotFound;
}
