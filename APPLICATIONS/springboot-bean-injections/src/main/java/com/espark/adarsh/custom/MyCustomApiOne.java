package com.espark.adarsh.custom;

@MyApiService(name="MyCustom1")
public class MyCustomApiOne implements CustomApi{

    String name;

    public MyCustomApiOne() {
        this.name="MyCustomApiOne";
    }
    public String getName() {
        return name;
    }
}
