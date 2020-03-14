package com.espark.adarsh.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "category")
@SolrDocument(solrCoreName = "category")
public class Category extends AbstractEntity implements TreeNode {

    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catId")
    @Field
    @Indexed(name = "catId", type = "long")
    private Long catId;

    @NotBlank
    @Column(name = "catName")
    @Field
    @Indexed(name = "catName", type = "string")
    private String catName;

    @NotBlank
    @Column(name = "catPath")
    @Field
    @Indexed(name = "catPath", type = "string")
    private String catPath;

    @Column(name = "level")
    @Field
    @Indexed(name = "level", type = "long")
    private Integer level;

    @Column(name = "parentCatId")
    @Field
    @Indexed(name = "parentCatId", type = "long")
    private Long parentCatId;


    public Category() {
        super(Type.category);
    }

    public Category(@NotBlank String catName,
                    Category parentCat) {
        super(Type.category,new Date(), new Date());
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
    }

    public Category(Long catId, @NotBlank String catName
            , @NotBlank String catPath, Integer level
            , Long parentCatId, Date createdDate, Date updatedDate) {
        super(Type.category,createdDate, updatedDate);
        this.catId = catId;
        this.catName = catName;
        this.catPath = catPath;
        this.level = level;
        this.parentCatId = parentCatId;
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

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.catName;
    }

    @Override
    public Long getId() {
        return this.getCatId();
    }

    @Override
    public Long getParent() {
        return this.parentCatId;
    }

    @Override
    public String getPath() {
        return this.catPath;
    }

    @Override
    @JsonIgnore
    public List getChildren() {
        return new LinkedList();
    }
}
