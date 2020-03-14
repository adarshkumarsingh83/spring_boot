package com.espark.adarsh.api;


import com.espark.adarsh.bean.ProductBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.service.SearchService;
import com.espark.adarsh.util.MessageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.espark.adarsh.util.ApplicationUtil.*;
@RestController
@Api(value = "SearchController", description = "Search Operations")
public class SearchController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/search/{keyword}")
    public ResponseBean<List<ProductBean>, String> getProduct(@PathVariable("keyword") String keyword)
            throws ResourceNotFound {
        List<ProductBean> productBeanResponse = this.searchService.searchProduct(keyword);
        return new ResponseBean(productBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{PRODUCT, SEARCH_PRODUCT}));
    }
}
