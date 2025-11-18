package com.espark.adarsh.service;

import com.espark.adarsh.bean.ApiBean;
import com.espark.adarsh.custom.CustomApi;
import com.espark.adarsh.custom.CustomApiBean;
import com.espark.adarsh.transformer.ApiTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiService {

    private Map<String, ApiBean> apiBeanMap;
    private Map<String, ApiTransformer> apiTransformerMap;
    private Map<String, CustomApi> customApiMap;
    private Map<String, CustomApiBean> customApiBeanMap;



    public ApiService(Map<String, ApiBean> apiBeanMap,
                      Map<String, CustomApi> customApiMap,
                      Map<String, CustomApiBean> customApiBeanMap,
                      Map<String, ApiTransformer> apiTransformerMap) {
        this.apiTransformerMap = apiTransformerMap;
        this.customApiMap = customApiMap;
        this.customApiBeanMap = customApiBeanMap;
        this.apiBeanMap = apiBeanMap;
    }

    public Map<String,ApiBean> getApiBeanMap() {
        return apiBeanMap;
    }


    public Map<String, ApiTransformer> getApiTransformerMap() {
        return apiTransformerMap;
    }

    public Map<String, CustomApi> getCustomApiMap() {
        return customApiMap;
    }

    public Map<String, CustomApiBean> getCustomApiBeanMap() {
        return customApiBeanMap;
    }
}
