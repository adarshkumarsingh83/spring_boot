package com.espark.adarsh.service;

import com.espark.adarsh.bean.TreeNode;
import com.espark.adarsh.entity.Node;

import java.util.List;

public interface TreeService {

    public static String EMP_TREE = "EMP_TREE";
    public static String DEPT_TREE = "DEPT_TREE";
    public static String ALL_TREE = "ALL_TREE";

    TreeNode<Node> getRoot(String type);

    TreeNode<Node> getRootWithChild(String type, int depth);

    List<TreeNode> getChildren(String type, int depth);
}
