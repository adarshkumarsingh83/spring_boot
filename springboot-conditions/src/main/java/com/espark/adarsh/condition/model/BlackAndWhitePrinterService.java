package com.espark.adarsh.condition.model;

public class BlackAndWhitePrinterService implements com.espark.adarsh.condition.model.PrintService {
    @Override
    public void printData(String data) {
        System.out.println("BlackAndWhitePrinterService => "+data);
    }
}
