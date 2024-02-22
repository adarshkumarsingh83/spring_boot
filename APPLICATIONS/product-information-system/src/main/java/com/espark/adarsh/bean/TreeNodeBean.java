package com.espark.adarsh.bean;

import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.TreeNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "id",
        "name",
        "path",
        "parentId",
        "type",
        "active",
        "date",
        "children"
})
public class TreeNodeBean<T extends TreeNode> {

    private T treeNode;

    private List<TreeNodeBean> children = new LinkedList<>();

    public TreeNodeBean(T treeNode) {
        this.treeNode = treeNode;
    }

    public TreeNodeBean(T treeNode, List<TreeNodeBean> children) {
        this.treeNode = treeNode;
        this.children = children;
    }

    @ApiModelProperty(position = 1, required = true, value = "Contains Id Node")
    @JsonProperty("id")
    public Long getId() {
        return this.treeNode.getId();
    }

    @ApiModelProperty(position = 2, required = true, value = "Contains Name Node")
    @JsonProperty("name")
    public String getName() {
        return this.treeNode.getName();
    }

    @ApiModelProperty(position = 3, required = true, value = "Contains Path Node")
    @JsonProperty("path")
    public String getPath() {
        return this.treeNode.getPath();
    }

    @ApiModelProperty(position = 4, required = true, value = "Contains Parent Id Node")
    @JsonProperty("parentId")
    public Long getParentId() {
        return this.treeNode.getParent();
    }

    @ApiModelProperty(position = 5, required = true, value = "Contains type of Node")
    @JsonProperty("type")
    public AbstractEntity.Type getType(){
        return this.treeNode.getType();
    }

    @ApiModelProperty(position = 6, required = true, value = "Contains active or inactive status of Node")
    @JsonProperty("active")
    public Boolean getActive() {
        return treeNode.getActive();
    }

    @ApiModelProperty(position = 7, required = true, value = "Contains the Data Node")
    @JsonProperty("date")
    public T getTreeNode() {
        return treeNode;
    }

    @ApiModelProperty(position = 8, required = true, value = "Contains children of Node")
    @JsonProperty("children")
    public List<TreeNodeBean> getChildren() {
        return children;
    }

    @JsonIgnore
    public void setChildren(TreeNodeBean children) {
        this.children.add(children);
    }

    public void setChildren(List<TreeNodeBean> children) {
        this.children.addAll(children);
    }

}
