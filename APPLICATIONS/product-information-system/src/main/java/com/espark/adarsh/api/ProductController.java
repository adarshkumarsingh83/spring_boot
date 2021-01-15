package com.espark.adarsh.api;

import com.espark.adarsh.bean.ProductBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.service.ProductService;
import com.espark.adarsh.service.SearchService;
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
public class ProductController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private SearchService searchService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/{id}")
    public ResponseBean<ProductBean, String> getProduct(@PathVariable("id") Long id)
            throws ResourceNotFound {
        ProductBean productBeanResponse = this.productService.getProduct(id);
        return new ResponseBean(productBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{PRODUCT, GET_PRODUCT}));
    }

    @PostMapping(value = "/product")
    public ResponseBean<ProductBean, String> saveProduct(
            @RequestBody ProductBean productBean)
            throws ResourceNotFound {
        ProductBean productBeanResponse = this.productService.saveProduct(productBean);
        return new ResponseBean(productBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{PRODUCT, POST_PRODUCT}));
    }

    @PutMapping(value = "/product/{id}")
    public ResponseBean<ProductBean, String> updateProduct(
            @PathVariable("id") Long id
            , @RequestBody ProductBean productBean)
            throws ResourceNotFound {
        ProductBean productBeanResponse = this.productService.updateProduct(id, productBean);
        return new ResponseBean(productBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{PRODUCT, PUT_PRODUCT}));
    }


    @DeleteMapping(value = "/product/{id}")
    public ResponseBean<ProductBean, String> deleteProduct(@PathVariable("id") Long id)
            throws ResourceNotFound {
        ProductBean productBeanResponse = this.productService.deleteProduct(id);
        return new ResponseBean(productBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{PRODUCT, DELETE_PRODUCT}));
    }


    @GetMapping(value = "/product/{searchTerm}/{start}/{max}")
    public ResponseBean<List<ProductBean>, String> searchProduct(@PathVariable("searchTerm") String searchTerm
            , @PathVariable("start") int start
            , @PathVariable("max") int max) throws ResourceNotFound {
        List<ProductBean> productBeanList = this.productService.searchProduct(searchTerm, start, max);
        return new ResponseBean(productBeanList,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{PRODUCT, GET_PRODUCT}));
    }

}
