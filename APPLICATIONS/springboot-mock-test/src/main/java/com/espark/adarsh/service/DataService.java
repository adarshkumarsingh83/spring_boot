package com.espark.adarsh.service;


import java.util.List;

public interface DataService {

    public List<Integer> retrieveAllData(int range);

    List<Integer> findEvenNumber(int range);

    List<Integer> findOddNumber(int range);
}
