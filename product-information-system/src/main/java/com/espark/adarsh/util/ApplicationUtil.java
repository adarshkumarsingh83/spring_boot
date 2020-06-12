package com.espark.adarsh.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public interface ApplicationUtil {

    public static final String HOME="department";
    public static final String CATEGORY="category";
    public static final String PRODUCT="product";
    public static final String DIMENSION="dimension";
    public static final String DIMENSION_VALUES="dimension_values";
    public static final String PRODUCT_CATEGORY="product_category";
    public static final String CATEGORY_NOT_FOUND="CATEGORY_NOT_FOUND";
    public static final String PRODUCT_NOT_FOUND="PRODUCT_NOT_FOUND";
    public static final String DIMENSION_NOT_FOUND="DIMENSION_NOT_FOUND";
    public static final String TREE_TYPE_NOT_FOUND="TREE_TYPE_NOT_FOUND";
    public static final String DATA_NOT_FOUND="DATA_NOT_FOUND";
    public static final String API_RESPONSE="API_RESPONSE";
    public static final String GET_TREE="get-tree";
    public static final String GET_PRODUCT="get-product";
    public static final String POST_PRODUCT="post-product";
    public static final String PUT_PRODUCT="put-product";
    public static final String DELETE_PRODUCT="delete-product";
    public static final String GET_CATEGORY="get-category";
    public static final String POST_CATEGORY="post-category";
    public static final String PUT_CATEGORY="put-category";
    public static final String DELETE_CATEGORY="delete-category";
    public static final String GET_DIMENSION="get-dimension";
    public static final String POST_DIMENSION="post-dimension";
    public static final String PUT_DIMENSION="put-dimension";
    public static final String DELETE_DIMENSION="delete-dimension";

    public static final String SEARCH_PRODUCT="search-product";

    public default String getJsonString(Object object) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    public default Object jsonToObject(String jsonString) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.readValue(jsonString, new TypeReference<Object>() { });
    }

}
