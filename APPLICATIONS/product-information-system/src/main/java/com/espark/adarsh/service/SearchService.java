package com.espark.adarsh.service;

import com.espark.adarsh.bean.ProductBean;
import com.espark.adarsh.bean.TreeNodeBean;
import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.exception.ResourceNotFound;

import java.util.List;

public interface SearchService {

    List<AbstractEntity> getListSearchResult(String searchTerm, String type, int startIndex, int maxSize);

    TreeNodeBean getTreeSearchResult(String searchTerm, String type) throws ResourceNotFound;

    List<ProductBean> searchProduct(String keywords)throws ResourceNotFound;
}
