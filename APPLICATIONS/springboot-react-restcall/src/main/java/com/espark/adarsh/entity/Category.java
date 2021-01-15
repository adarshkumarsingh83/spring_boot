package com.espark.adarsh.entity;


import javax.validation.constraints.NotBlank;
import java.util.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "category")
public class Category {

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long catId;

    @javax.validation.constraints.NotBlank
    private String catName;

    @javax.validation.constraints.NotBlank
    private String catPath;

    private Integer level;

    private Long parentCatId;

    @javax.persistence.Column(nullable = false, updatable = false)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @org.springframework.data.annotation.CreatedDate
    private java.util.Date createdDate;

    @javax.persistence.Column(nullable = false)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @org.springframework.data.annotation.LastModifiedDate
    private java.util.Date updatedDate;

    public Category() {
    }

    public Category(@javax.validation.constraints.NotBlank String catName,
                    Category parentCat) {
        this.catName = catName;
        if (parentCat != null) {
            this.parentCatId = parentCat.getCatId();
            this.catPath = parentCat.getCatPath() + "/" + catName;
            this.level = parentCat.getLevel() + 1;
        } else {
            this.parentCatId = 0L;
            this.catPath = "/" + this.catName;
            this.level = 0;
        }
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public Category(Long catId, @NotBlank String catName
            , @NotBlank String catPath, Integer level
            , Long parentCatId, Date createdDate, Date updatedDate) {
        this.catId = catId;
        this.catName = catName;
        this.catPath = catPath;
        this.level = level;
        this.parentCatId = parentCatId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    public java.util.Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(java.util.Date updatedDate) {
        this.updatedDate = updatedDate;
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
}
