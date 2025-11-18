package com.espark.adarsh.custom;

@MyApiService(name="MyCustom2")
public class MyCustomApiTwo implements CustomApi{

    String name;

    public MyCustomApiTwo() {
        this.name="MyCustomApiTwo";
    }
    public String getName() {
        return name;
    }

}
