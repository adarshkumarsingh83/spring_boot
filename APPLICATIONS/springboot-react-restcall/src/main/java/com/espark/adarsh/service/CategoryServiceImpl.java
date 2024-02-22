package com.espark.adarsh.service;

import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.entity.Category;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.repostiory.CategoryRepository;
import com.espark.adarsh.util.ApplicationUtil;
import com.espark.adarsh.util.TreeUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    TreeUtilService treeUtilService;

    @Override
    @Transactional
    public CategoryBean saveCategory(CategoryBean categoryBean) throws ValidationException {
        Category category = categoryBean.getCategory();
        Category parentCategory = this.getParent(categoryBean);
        category.setCatPath(parentCategory.getCatPath() + "/" + category.getCatName());
        category.setLevel(parentCategory.getLevel() + 1);
        category.setCreatedDate(new Date());
        category.setUpdatedDate(new Date());
        category = this.categoryRepository.save(category);
        return new CategoryBean(category);
    }

    @Override
    @Transactional
    public List<CategoryBean> getCategory() throws ResourceNotFound {
        Iterable<Category> iterable = this.categoryRepository.findAll();
        List<CategoryBean> categoryBeanList = new ArrayList<>();
        iterable.forEach(category -> {
            categoryBeanList.add(new CategoryBean(category));
        });
        return categoryBeanList;
    }

    @Override
    @Transactional
    public CategoryBean updateCategory(Long categoryId, CategoryBean categoryBean)
            throws ResourceNotFound, ValidationException {
        if (this.categoryRepository.existsById(categoryId)) {
            Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
            Category currentCategory = categoryOptional.get();
            Category category = categoryBean.getCategory();
            category.setCatId(currentCategory.getCatId());
            category.setUpdatedDate(new Date());
            Category parentCategory = this.getParent(categoryBean);
            category.setCatPath(parentCategory.getCatPath() + "/" + currentCategory.getCatName());
            category.setLevel(parentCategory.getLevel() + 1);
            category = this.categoryRepository.save(category);
            return new CategoryBean(category);
        } else {
            throw new ResourceNotFound("Category Not Found For " + categoryId);
        }
    }

    @Override
    @Transactional
    public CategoryBean getCategory(Long categoryId) throws ResourceNotFound {
        com.espark.adarsh.entity.Category category = null;
        if (this.categoryRepository.existsById(categoryId)) {
            Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
            category = categoryOptional.get();
        } else {
            throw new ResourceNotFound("Category Not Found For " + categoryId);
        }
        return new CategoryBean(category);
    }

    @Override
    @Transactional
    public CategoryBean deleteCategory(Long categoryId) throws ResourceNotFound {
        com.espark.adarsh.entity.Category category = null;
        if (this.categoryRepository.existsById(categoryId)) {
            Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
            category = categoryOptional.get();
            this.categoryRepository.deleteById(categoryId);
        } else {
            throw new ResourceNotFound("Category Not Found For " + categoryId);
        }
        return new CategoryBean(category);
    }


    @Override
    @Transactional
    public CategoryBean getCategoryTree() {
        Category rootCategory = this.categoryRepository.findByCatName(ApplicationUtil.HOME);
        List<Category> childCategory = this.categoryRepository.findByCatPathStartingWith(rootCategory.getCatPath());
        return this.treeUtilService.getTree(rootCategory, childCategory);
    }

    @Override
    @Transactional
    public CategoryBean getCategoryTree(Long categoryId) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category rootCategory = categoryOptional.get();
            //fetch all child node
            List<Category> childCategory = this.categoryRepository.findByCatPathStartingWith(rootCategory.getCatPath());
            //construct a tree from them
            return this.treeUtilService.getTree(rootCategory, childCategory);
        }
        return null;
    }

    @Transactional
    private Category getParent(CategoryBean categoryBean) throws ValidationException {
        Category category = categoryBean.getCategory();
        try {
            Long parentCatId = category.getParentCatId();
            Optional<Category> categoryOptional= this.categoryRepository.findById(parentCatId);
            if (!categoryOptional.isPresent()) {
                throw new ResourceNotFound("Parent Category Not Found For " + parentCatId);
            }else{
                category=categoryOptional.get();
            }
        } catch (Exception e) {
            throw new ValidationException("Parent Category Not Found ");
        }
        return category;
    }
}
