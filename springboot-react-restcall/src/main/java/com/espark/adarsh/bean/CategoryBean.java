package com.espark.adarsh.bean;

import com.espark.adarsh.entity.Category;
import com.fasterxml.jackson.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryBean implements Serializable {

    private Long catId;

    private String catName;

    private Long parentCatId;

    private List<CategoryBean> childCategory = new LinkedList<>();

    private String catPath;

    private Integer level;

    private java.util.Date createdDate;

    private java.util.Date updatedDate;

    public CategoryBean() {
    }

    public CategoryBean(Category category) {
        this.catId = category.getCatId();
        this.catName = category.getCatName();
        this.catPath = category.getCatPath();
        this.level = category.getLevel();
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

    public void setChildCategoryBean(List<CategoryBean> childCategory) {
        this.childCategory = childCategory;
    }

    @JsonIgnore
    public void setChildCategoryBean(CategoryBean childCategoryBean) {
        this.childCategory.add(childCategoryBean);
    }

    public String getCatPath() {
        return catPath;
    }

    public void setCatPath(String catPath) {
        this.catPath = catPath;
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
