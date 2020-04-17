package com.espark.adarsh.bean;

import java.util.LinkedList;
import java.util.List;

public class SearchBean <T> {

    private Class<T> classType;

    private List<SearchCriteria> andSearchCriteria = new LinkedList<>();

    private List<SearchCriteria> orSearchCriteria = new LinkedList<>();

    private List<RangeCriteria> rangeCriteria = new LinkedList<>();

    private List<InBean> inList=new LinkedList<>();

    private Integer pageNumber;

    private Integer pageSize;

    public SearchBean() {
    }

    public Class<T> getClassType() {
        return classType;
    }

    public void setClassType(Class<T> classType) {
        this.classType = classType;
    }

    public List<SearchCriteria> getAndSearchCriteria() {
        return andSearchCriteria;
    }

    public void setAndSearchCriteria(List<SearchCriteria> andSearchCriteria) {
        this.andSearchCriteria = andSearchCriteria;
    }

    public List<SearchCriteria> getOrSearchCriteria() {
        return orSearchCriteria;
    }

    public void setOrSearchCriteria(List<SearchCriteria> orSearchCriteria) {
        this.orSearchCriteria = orSearchCriteria;
    }

    public List<RangeCriteria> getRangeCriteria() {
        return rangeCriteria;
    }

    public void setRangeCriteria(List<RangeCriteria> rangeCriteria) {
        this.rangeCriteria = rangeCriteria;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<InBean> getInList() {
        return inList;
    }

    public void setInList(List<InBean> inList) {
        this.inList = inList;
    }
}
