package com.espark.adarsh.web;

import com.espark.adarsh.bean.ApiBean;
import com.espark.adarsh.custom.CustomApi;
import com.espark.adarsh.service.ApiService;
import com.espark.adarsh.transformer.ApiTransformer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiController {

    ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/api/beans")
    public Map<String, ApiBean> getApiService() {
        return apiService.getApiBeanMap();
    }

    @GetMapping("/api/transformers")
    public Map<String, ApiTransformer> getApiTransformers() {
        return apiService.getApiTransformerMap();
    }

    @GetMapping("/api/customApis")
    public Map<String, CustomApi> getCustomApis() {
        return apiService.getCustomApiMap();
    }

    @GetMapping("/api/customApiBeans")
    public Map<String, com.espark.adarsh.custom.CustomApiBean> getCustomApiBeans() {
        return apiService.getCustomApiBeanMap();
    }

}
