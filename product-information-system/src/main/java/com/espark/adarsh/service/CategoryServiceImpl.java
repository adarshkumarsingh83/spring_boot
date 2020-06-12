package com.espark.adarsh.service;

import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.Category;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.repostiory.db.CategoryRepository;
import com.espark.adarsh.repostiory.solr.SearchCategoryRepository;
import com.espark.adarsh.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.espark.adarsh.util.ApplicationUtil.*;

@Slf4j
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private SearchService searchService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SearchCategoryRepository searchCategoryRepository;

    @Override
    public CategoryBean saveCategory(CategoryBean categoryBean) {
        Category categorySaved = this.categoryRepository.save(categoryBean.getCategory());
        this.searchCategoryRepository.save(categorySaved);
        return new CategoryBean(categorySaved);
    }

    @Override
    public List<CategoryBean> getCategory() throws ResourceNotFound {
        Iterable<Category> iterable = this.categoryRepository.findAll();
        List<CategoryBean> categoryBeanList = new ArrayList<>();
        iterable.forEach(category -> {
            categoryBeanList.add(new CategoryBean(category));
        });
        return categoryBeanList;
    }

    @Override
    public CategoryBean updateCategory(Long categoryId, CategoryBean categoryBean)
            throws com.espark.adarsh.exception.ResourceNotFound {
        if (this.categoryRepository.existsById(categoryId)) {
            Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
            Category currentCategory = categoryOptional.get();
            categoryBean.setCatId(currentCategory.getCatId());
            Category categorySaved = this.categoryRepository.save(categoryBean.getCategory());
            this.searchCategoryRepository.save(categorySaved);
            return new CategoryBean(categorySaved);
        } else {
            throw new ResourceNotFound(CATEGORY_NOT_FOUND, new Object[]{categoryId});
        }
    }

    @Override
    public CategoryBean getCategory(Long categoryId) throws ResourceNotFound {
        Category category = null;
        if (this.categoryRepository.existsById(categoryId)) {
            Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
            category = categoryOptional.get();
        } else {
            throw new ResourceNotFound(CATEGORY_NOT_FOUND, new Object[]{categoryId});
        }
        return new CategoryBean(category);
    }

    @Override
    public Category findByCatName(String catName) throws ResourceNotFound {
        Category category = this.categoryRepository.findByCatName(catName);
        if (category == null) {
            throw new ResourceNotFound(CATEGORY_NOT_FOUND, new Object[]{catName});
        }
        return category;
    }


    @Override
    public CategoryBean deleteCategory(Long categoryId) throws ResourceNotFound {
        Category category = null;
        if (this.categoryRepository.existsById(categoryId)) {
            Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
            category = categoryOptional.get();
            this.categoryRepository.deleteById(categoryId);
            this.searchCategoryRepository.deleteById(categoryId);
        } else {
            throw new ResourceNotFound(CATEGORY_NOT_FOUND, new Object[]{categoryId});
        }
        return new CategoryBean(category);
    }


    @Override
    public List<CategoryBean> searchCategory(String searchTerm, int start, int max) throws ResourceNotFound {
        List<AbstractEntity> list = this.searchService.getListSearchResult(searchTerm, CATEGORY, start, max);
        List<CategoryBean> response = list.stream()
                .map((AbstractEntity bean) -> new CategoryBean((Category) bean))
                .collect(Collectors.toList());
        return response;
    }


    /* AbstractService implementation for generic tree support  */
    @Override
    public Category getRoot() {
        return this.categoryRepository.findByCatName(ApplicationUtil.HOME);
    }

    @Override
    public List getNextLevelChildren(Long id) {
        return this.categoryRepository.findByParentCatId(id);
    }

    @Override
    public List getAllLevelChildren(Long id) throws ResourceNotFound {
        Category category = this.getById(id);
        return this.categoryRepository.findByCatPathStartingWith(category.getCatPath());
    }

    @Override
    public Category getById(Long id) throws ResourceNotFound {
        Optional<Category> response = this.categoryRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        } else {
            throw new ResourceNotFound(CATEGORY_NOT_FOUND, new Object[]{id});
        }
    }

    @Override
    public List<Category> searchByKeyWord(String keyWord) throws ResourceNotFound {
        Page<Category> categoryPage = this.searchCategoryRepository
                .findByCatNameContaining(keyWord, new PageRequest(0, 1000));
        if (categoryPage.getTotalElements() > 0) {
            return categoryPage.getContent();
        } else {
            throw new ResourceNotFound(CATEGORY_NOT_FOUND, new Object[]{keyWord});
        }
    }
}
