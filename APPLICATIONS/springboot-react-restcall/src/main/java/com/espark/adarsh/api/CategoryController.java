package com.espark.adarsh.api;

import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category/{id}")
    public com.espark.adarsh.bean.CategoryBean getCategory(@PathVariable("id") Long id)
            throws ResourceNotFound {
        return this.categoryService.getCategory(id);
    }

    @GetMapping(value = "/category")
    public List<CategoryBean> getCategory() throws ResourceNotFound {
        return this.categoryService.getCategory();
    }

    @GetMapping(value = "/category-tree/{id}")
    public CategoryBean getCategoryTree(@PathVariable("id") Long id) {
        return this.categoryService.getCategoryTree(id);
    }

    @GetMapping(value = "/category-tree")
    public CategoryBean getCategoryTree() {
        return this.categoryService.getCategoryTree();
    }

    @PostMapping(value = "/category")
    public CategoryBean saveCategory(@RequestBody CategoryBean categoryBean)
            throws ResourceNotFound, ValidationException {
        return this.categoryService.saveCategory(categoryBean);
    }

    @PutMapping(value = "/category/{id}")
    public CategoryBean updateCategory(@PathVariable("id") Long id,
                                       @RequestBody CategoryBean categoryBean)
            throws ResourceNotFound, ValidationException {
        return this.categoryService.updateCategory(id, categoryBean);
    }


    @DeleteMapping(value = "/category/{id}")
    public CategoryBean deleteCategory(@PathVariable("id") Long id)
            throws ResourceNotFound {
        return this.categoryService.deleteCategory(id);
    }



}
