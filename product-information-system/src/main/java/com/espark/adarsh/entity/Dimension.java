package com.espark.adarsh.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "dimension")
@SequenceGenerator(name = "DimSeq", initialValue = 1000, allocationSize = 1)
@SolrDocument(solrCoreName = "dimension")
public class Dimension extends AbstractEntity implements TreeNode {

    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DimSeq")
    @Column(name = "dimId")
    @Field
    @Indexed(name = "dimId", type = "long")
    private Long dimId;

    @NotBlank
    @Column(name = "dimName")
    @Field
    @Indexed(name = "dimName", type = "string")
    private String dimName;

    @Column(name = "hierarchical")
    @Field
    @Indexed(name = "hierarchical", type = "string")
    private Boolean hierarchical;

    @Column(name = "parentDimId")
    @Field
    @Indexed(name = "parentDimId", type = "long")
    private Long parentDimId;

    @Column(name = "level")
    private Integer level;

    @NotBlank
    @Column(name = "dimPath")
    @Field
    @Indexed(name = "dimPath", type = "string")
    private String dimPath;

    @JsonIgnore
    @Transient
    private List<DimensionValue> children = new LinkedList<>();


    public Dimension() {
        super(Type.dimension);
    }

    public Dimension(Long dimId, @NotBlank String dimName, Boolean hierarchical, Long parentDimId, Integer level, String dimParentPath) {
        super(Type.dimension, new Date(), new Date());
        this.dimId = dimId;
        this.dimName = dimName;
        this.hierarchical = hierarchical;
        this.parentDimId = parentDimId;
        this.level = level;
        this.dimPath = dimParentPath + "/" + dimName;
    }

    public Long getDimId() {
        return dimId;
    }

    public void setDimId(Long dimId) {
        this.dimId = dimId;
    }

    public String getDimName() {
        return dimName;
    }

    public void setDimName(String dimName) {
        this.dimName = dimName;
    }

    public Boolean getHierarchical() {
        return hierarchical;
    }

    public void setHierarchical(Boolean hierarchical) {
        this.hierarchical = hierarchical;
    }

    public Long getParentDimId() {
        return parentDimId;
    }

    public void setParentDimId(Long parentDimId) {
        this.parentDimId = parentDimId;
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

    @JsonIgnore
    public void setChildren(DimensionValue children) {
        this.children.add(children);
    }

    @JsonIgnore
    public void setChildren(List<DimensionValue> children) {
        this.children = children;
    }

    @Override
    public Long getParent() {
        return this.parentDimId;
    }

    @Override
    public Long getId() {
        return this.dimId;
    }

    @Override
    public String getName() {
        return this.dimName;
    }

    @Override
    public String getPath() {
        return this.dimPath;
    }

    @JsonIgnore
    @Override
    public List getChildren() {
        return this.children;
    }
}
