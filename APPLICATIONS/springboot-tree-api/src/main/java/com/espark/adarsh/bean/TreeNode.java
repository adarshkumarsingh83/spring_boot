package com.espark.adarsh.bean;

import com.espark.adarsh.entity.Node;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode<T extends Node> {

    private T data;
    private List<TreeNode> children;

    public TreeNode() {
    }

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, List<TreeNode> children) {
        this.data = data;
        this.children = children;
    }

    public void setChildren(TreeNode child) {
        if (this.children == null) {
            this.children = new LinkedList<>();
        }
        this.children.add(child);
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
