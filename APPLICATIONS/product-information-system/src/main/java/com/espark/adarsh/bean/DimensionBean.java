package com.espark.adarsh.bean;

import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.Dimension;
import com.espark.adarsh.entity.DimensionValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DimensionBean implements Serializable {

    @JsonProperty("dimId")
    private Long dimId;
    @JsonProperty("dimName")
    private String dimName;
    @JsonProperty("hierarchical")
    private Boolean hierarchical;
    @JsonProperty("parentDimId")
    private Long parentDimId;
    @JsonProperty("level")
    private Integer level;
    @JsonProperty("type")
    private AbstractEntity.Type type;
    @JsonProperty("childDimensionBean")
    private List<DimensionBean> childDimensionBean;
    @JsonProperty("dimPath")
    private String dimPath;
    @JsonProperty("dimensionValues")
    private List<DimensionValues> dimensionValues;

    public DimensionBean() {
    }

    public DimensionBean(Dimension dimension) {
        this.dimId = dimension.getDimId();
        this.dimName = dimension.getDimName();
        this.hierarchical = dimension.getHierarchical();
        this.parentDimId = dimension.getParentDimId();
        this.level = dimension.getLevel();
        this.dimPath = dimension.getPath();
    }

    public DimensionBean(Long dimId, DimensionValue dimensionValue) {
        this.dimId = dimId;
        this.dimensionValues = new LinkedList<>();
        dimensionValues.add(new DimensionValues(dimensionValue));
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

    public List<DimensionBean> getChildDimensionBean() {
        return childDimensionBean;
    }

    public void setChildDimensionBean(List<DimensionBean> childDimensionBean) {
        this.childDimensionBean = childDimensionBean;
    }

    public List<DimensionValues> getDimensionValues() {
        return dimensionValues;
    }

    public void setDimensionValues(List<DimensionValues> dimensionValues) {
        this.dimensionValues = dimensionValues;
    }

    public String getDimPath() {
        return dimPath;
    }

    public void setDimPath(String dimPath) {
        this.dimPath = dimPath;
    }

    @JsonIgnore
    public Dimension getDimension() {
        return new Dimension(this.getDimId(), this.getDimName(), this.getHierarchical(), this.getParentDimId(), this.getLevel(), this.getDimPath());
    }

    @JsonIgnore
    public void setDimension(Dimension dimension) {
        this.dimId = dimension.getDimId();
        this.dimName = dimension.getDimName();
        this.hierarchical = dimension.getHierarchical();
        this.parentDimId = dimension.getParentDimId();
        this.level = dimension.getLevel();
        this.dimPath = dimension.getPath();
    }

    @JsonIgnore
    public List<DimensionValue> getDimensionValue() {
        List<DimensionValue> valueList = new LinkedList<>();
        if (this.dimensionValues != null && !dimensionValues.isEmpty()) {
            valueList = this.dimensionValues.stream()
                    .map((DimensionValues dimensionValue) -> new DimensionValue(dimensionValue.getDimValId()
                            , dimensionValue.getDimValName(), this.getDimId(), this.getDimPath()))
                    .collect(Collectors.toList());
        }
        return valueList;
    }


    public static class DimensionValues {

        private Long dimValId;

        private String dimValName;

        private String dimValuePath;

        public DimensionValues(DimensionValue dimensionValue) {
            this.dimValId = dimensionValue.getDimId();
            this.dimValName = dimensionValue.getDimValName();
            this.dimValuePath = dimensionValue.getPath();
        }

        public DimensionValues(Long dimValId, String dimValName, String dimValuePath) {
            this.dimValId = dimValId;
            this.dimValName = dimValName;
            this.dimValuePath = dimValuePath;
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

        public String getDimValuePath() {
            return dimValuePath;
        }

        public void setDimValuePath(String dimValuePath) {
            this.dimValuePath = dimValuePath;
        }
    }
}
