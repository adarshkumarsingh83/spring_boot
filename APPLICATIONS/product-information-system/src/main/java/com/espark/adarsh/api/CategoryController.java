package com.espark.adarsh.api;

import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.service.CategoryService;
import com.espark.adarsh.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.espark.adarsh.util.ApplicationUtil.*;


@RestController
public class CategoryController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category/{id}")
    public ResponseBean<CategoryBean, String> getCategory(@PathVariable("id") Long id)
            throws ResourceNotFound {
        CategoryBean categoryBeanResponse = this.categoryService.getCategory(id);
        return new ResponseBean(categoryBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{CATEGORY, GET_CATEGORY}));
    }

    @GetMapping(value = "/category")
    public ResponseBean<List<CategoryBean>, String> getCategory() throws ResourceNotFound {
        List<CategoryBean> categoryBeanList = this.categoryService.getCategory();
        return new ResponseBean(categoryBeanList,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{CATEGORY, GET_CATEGORY}));
    }

    @PostMapping(value = "/category")
    public ResponseBean<CategoryBean, String> saveCategory(
            @RequestBody CategoryBean categoryBean)
            throws ResourceNotFound {
        CategoryBean categoryBeanResponse = this.categoryService.saveCategory(categoryBean);
        return new ResponseBean(categoryBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{CATEGORY, POST_CATEGORY}));
    }

    @PutMapping(value = "/category/{id}")
    public ResponseBean<CategoryBean, String> updateCategory(
            @PathVariable("id") Long id,
            @RequestBody CategoryBean categoryBean)
            throws ResourceNotFound {
        CategoryBean categoryBeanResponse = this.categoryService.updateCategory(id, categoryBean);
        return new ResponseBean(categoryBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{CATEGORY, PUT_CATEGORY}));
    }


    @DeleteMapping(value = "/category/{id}")
    public ResponseBean<CategoryBean, String> deleteCategory(@PathVariable("id") Long id)
            throws ResourceNotFound {
        CategoryBean categoryBeanResponse = this.categoryService.deleteCategory(id);
        return new ResponseBean(categoryBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{CATEGORY, DELETE_CATEGORY}));
    }


    @GetMapping(value = "/category/{searchTerm}/{start}/{max}")
    public ResponseBean<List<CategoryBean>, String> searchCategory(@PathVariable("searchTerm") String searchTerm
            , @PathVariable("start") int start
            , @PathVariable("max") int max) throws ResourceNotFound {
        List<CategoryBean> categoryBeanList = this.categoryService.searchCategory(searchTerm, start, max);
        return new ResponseBean(categoryBeanList,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{CATEGORY, GET_CATEGORY}));
    }

}
