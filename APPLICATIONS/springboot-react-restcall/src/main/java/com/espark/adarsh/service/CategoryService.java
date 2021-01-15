package com.espark.adarsh.service;

import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.exception.ResourceNotFound;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface CategoryService {

    CategoryBean saveCategory(CategoryBean category) throws ValidationException;

    List<CategoryBean> getCategory() throws ResourceNotFound;

    CategoryBean updateCategory(Long categoryId,CategoryBean category) throws ResourceNotFound, ValidationException;

    CategoryBean getCategory(Long categoryId) throws ResourceNotFound;

    CategoryBean deleteCategory(Long CategoryId) throws ResourceNotFound;

    CategoryBean getCategoryTree();

    CategoryBean getCategoryTree(Long categoryId);
}
