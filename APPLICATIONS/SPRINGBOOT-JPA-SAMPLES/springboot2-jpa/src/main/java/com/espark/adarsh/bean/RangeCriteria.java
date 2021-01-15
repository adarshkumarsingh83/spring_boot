package com.espark.adarsh.bean;

public class RangeCriteria{

    private String fieldName;
    private Object lowerRange;
    private Object upperRange;
    private boolean date;
    private boolean number;


    public RangeCriteria() {
    }

    public RangeCriteria(String fieldName, Object lowerRange, Object upperRange, boolean date, boolean number) {
        this.fieldName = fieldName;
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.date = date;
        this.number = number;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getLowerRange() {
        return lowerRange;
    }

    public void setLowerRange(Object lowerRange) {
        this.lowerRange = lowerRange;
    }

    public Object getUpperRange() {
        return upperRange;
    }

    public void setUpperRange(Object upperRange) {
        this.upperRange = upperRange;
    }

    public boolean isDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    public boolean isNumber() {
        return number;
    }

    public void setNumber(boolean number) {
        this.number = number;
    }
}
