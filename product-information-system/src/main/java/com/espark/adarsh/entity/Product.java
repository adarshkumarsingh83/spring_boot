package com.espark.adarsh.entity;

import com.espark.adarsh.util.CollectionJpaConverterJson;
import com.espark.adarsh.util.MapJpaConverterJson;
import com.espark.adarsh.util.PriceConverterJson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "product")
@SolrDocument(solrCoreName = "product")
public class Product extends AbstractEntity implements TreeNode {

    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prodId")
    @Field
    @Indexed(name = "prodId", type = "long")
    private Long prodId;

    @NotBlank
    @Column(name = "productName")
    @Field
    @Indexed(name = "productName", type = "string")
    private String productName;

    @Column(name = "catId")
    @Field
    @Indexed(name = "catId", type = "long")
    private Long catId;

    @Column(name = "inStock")
    @Field
    @Indexed(name = "inStock", type = "string")
    private Boolean inStock;

    @Column(name = "quantityInStock")
    @Field
    @Indexed(name = "quantityInStock", type = "long")
    private Long quantityInStock;

    @Convert(converter = PriceConverterJson.class)
    @Column(name = "priceDetail")
    @Field
    @Indexed(name = "priceDetail", type = "text_general")
    private Price priceDetail;

    @Convert(converter = CollectionJpaConverterJson.class)
    @Column(name = "categories")
    @Field
    @Indexed(name = "categories", type = "text_general")
    private List<Long> categories = new LinkedList<>();

    @Convert(converter = CollectionJpaConverterJson.class)
    @Column(name = "dimensions")
    @Field
    @Indexed(name = "dimensions", type = "text_general")
    private List<Long> dimensions = new LinkedList<>();

    @Convert(converter = MapJpaConverterJson.class)
    private Map<String, Object> metaData = new LinkedHashMap<>();

    public Product() {
        super(Type.product, new Date(), new Date());

    }

    public Product(Long prodId, @NotBlank String productName,
                   Long catId, Date createdDate, Date updatedDate) {
        super(Type.product, createdDate, updatedDate);
        this.prodId = prodId;
        this.productName = productName;
        this.categories.add(catId);
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

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Map<String, Object> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, Object> metaData) {
        this.metaData = metaData;
    }

    public List<Long> getCategories() {
        return categories;
    }

    @JsonIgnore
    public void setCategory(Long category) {
        this.categories.add(category);
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<Long> getDimensions() {
        return dimensions;
    }

    public void setDimension(Long dimension) {
        this.dimensions.add(dimension);
    }

    public void setDimensions(List<Long> dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    @Field
    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.productName;
    }

    @Override
    public Long getId() {
        return this.prodId;
    }

    @Override
    public Long getParent() {
        return this.getCatId();
    }

    @Override
    public String getPath() {
        return "";
    }

    @Override
    @JsonIgnore
    public List getChildren() {
        return new LinkedList();
    }


    public static class Price {

        @Column(name = "priceType")
        private String priceType;
        @Column(name = "minPrice")
        private String minPrice;
        @Column(name = "maxPrice")
        private String maxPrice;
        @Column(name = "orgPrice")
        private String orgPrice;
        @Column(name = "currentPrice")
        private String currentPrice;

        public String getPriceType() {
            return priceType;
        }

        public void setPriceType(String priceType) {
            this.priceType = priceType;
        }

        public String getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(String minPrice) {
            this.minPrice = minPrice;
        }

        public String getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(String maxPrice) {
            this.maxPrice = maxPrice;
        }

        public String getOrgPrice() {
            return orgPrice;
        }

        public void setOrgPrice(String orgPrice) {
            this.orgPrice = orgPrice;
        }

        public String getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }
    }

    public static class Image {

    }
}
