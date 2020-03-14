package com.espark.adarsh.bean;

import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.Category;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryBean implements Serializable {

    @JsonProperty("catId")
    private Long catId;

    @JsonProperty("catName")
    private String catName;

    @JsonProperty("parentCatId")
    private Long parentCatId;

    @JsonProperty("childCategory")
    private List<CategoryBean> childCategory = new LinkedList<>();

    @JsonProperty("products")
    private ProductBean products;

    @JsonProperty("catPath")
    private String catPath;

    @JsonProperty("type")
    private AbstractEntity.Type type;

    @JsonProperty("catLevel")
    private Integer level;

    @JsonProperty("createdDate")
    private java.util.Date createdDate;

    @JsonProperty("updatedDate")
    private java.util.Date updatedDate;

    public CategoryBean() {
    }

    public CategoryBean(Category category) {
        this.catId = category.getCatId();
        this.catName = category.getCatName();
        this.catPath = category.getCatPath();
        this.level = category.getLevel();
        this.type=category.getType();
        this.parentCatId = category.getParentCatId();
        this.createdDate = category.getCreatedDate();
        this.updatedDate = category.getUpdatedDate();
    }

    @JsonIgnore
    public Category getCategory() {
        return new Category(catId, catName, catPath, level, parentCatId, createdDate, updatedDate);
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(Long parentCatId) {
        this.parentCatId = parentCatId;
    }

    public List<CategoryBean> getChildCategory() {
        return childCategory;
    }

    public void setChildCategories(List<CategoryBean> childCategory) {
        this.childCategory = childCategory;
    }

    public void setChildCategory(CategoryBean childCategory) {
        this.childCategory.add(childCategory);
    }

    public ProductBean getProducts() {
        return products;
    }

    public void setProducts(ProductBean products) {
        this.products = products;
    }

    public String getCatPath() {
        return catPath;
    }

    public void setCatPath(String catPath) {
        this.catPath = catPath;
    }

    public AbstractEntity.Type getType() {
        return type;
    }

    public void setType(AbstractEntity.Type type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
