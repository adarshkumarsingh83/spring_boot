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
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "dimensionvalue")
@SequenceGenerator(name = "DimValueSeq", initialValue = 5000, allocationSize = 1)
@SolrDocument(solrCoreName = "dimensionvalue")
public class DimensionValue extends AbstractEntity implements TreeNode {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DimValueSeq")
    @Column(name = "dimValId")
    @Field
    @Indexed(name = "dimValId", type = "long")
    private Long dimValId;

    @NotBlank
    @Column(name = "dimValName")
    @Field
    @Indexed(name = "dimValName", type = "string")
    private String dimValName;

    @Column(name = "dimId")
    @Field
    @Indexed(name = "dimId", type = "long")
    private Long dimId;

    @NotBlank
    @Column(name = "dimValuePath")
    @Field
    @Indexed(name = "dimValuePath", type = "string")
    private String dimValuePath;


    public DimensionValue() {
        super(Type.dimensionValue);
    }

    public DimensionValue(Long dimValId, @NotBlank String dimValName, Long dimId, String path) {
        super(Type.dimensionValue, new Date(), new Date());
        this.dimValId = dimValId;
        this.dimValName = dimValName;
        this.dimId = dimId;
        this.dimValuePath = path + "/" + dimValName;
    }

    public Long getDimValId() {
        return dimValId;
    }

    public void setDimValId(Long dimValId) {
        this.dimValId = dimValId;
    }

    public String getDimValName() {
        return dimValName;
    }

    public void setDimValName(String dimValName) {
        this.dimValName = dimValName;
    }

    public Long getDimId() {
        return dimId;
    }

    public void setDimId(Long dimId) {
        this.dimId = dimId;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public Long getParent() {
        return this.dimId;
    }

    @Override
    public Long getId() {
        return this.dimValId;
    }

    @Override
    public String getName() {
        return this.dimValName;
    }

    @Override
    public String getPath() {
        return this.dimValuePath;
    }

    @JsonIgnore
    @Override
    public List getChildren() {
        return new LinkedList();
    }
}
