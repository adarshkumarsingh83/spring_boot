package com.espark.adarsh.bean;

import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.AbstractEntity.Type;
import com.espark.adarsh.entity.Product;
import com.fasterxml.jackson.annotation.*;
import org.springframework.data.solr.core.mapping.Indexed;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductBean {

    @JsonProperty("prodId")
    private Long prodId;

    @Indexed(name = "name", type = "string")
    @JsonProperty("productName")
    private String productName;

    @JsonProperty("catId")
    private Long catId;

    @JsonProperty("type")
    private AbstractEntity.Type type;

    @JsonProperty("createdDate")
    private Date createdDate;

    @JsonProperty("updatedDate")
    private Date updatedDate;

    public ProductBean() {
    }

    public ProductBean(Product product) {
        this.prodId = product.getProdId();
        this.productName = product.getProductName();
        this.catId = product.getCatId();
        this.type = product.getType();
        this.createdDate = product.getCreatedDate();
        this.updatedDate = product.getUpdatedDate();
    }

    @JsonIgnore
    public Product getProduct() {
        return new Product(prodId, productName, catId, createdDate, updatedDate);
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
