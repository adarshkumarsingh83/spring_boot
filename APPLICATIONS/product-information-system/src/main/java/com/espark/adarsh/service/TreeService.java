package com.espark.adarsh.service;

import com.espark.adarsh.bean.TreeNodeBean;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.exception.ValidationFailed;

import java.util.Optional;

public interface TreeService {

    public TreeNodeBean getTree(String treeType, Optional<Integer> depthParameter)
            throws ValidationFailed, ResourceNotFound;

    public TreeNodeBean getTree(String treeType, Long categoryId, Optional<Integer> depthParameter)
            throws ValidationFailed, ResourceNotFound;
}
