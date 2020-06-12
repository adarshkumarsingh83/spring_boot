package com.espark.adarsh.condition.model;

public class ColorPrinterService implements PrintService{

    @Override
    public void printData(String data) {
        System.out.println("ColorPrinterService => "+data);
    }
}
