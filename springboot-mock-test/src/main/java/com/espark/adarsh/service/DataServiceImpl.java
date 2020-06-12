package com.espark.adarsh.service;

import com.espark.adarsh.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    DataRepository dataRepository;

    @Override
    public List<Integer> retrieveAllData(int range){
        return this.dataRepository.retrieveAllData(range);
    }

    @Override
    public List<Integer> findEvenNumber(int range){
        return this.dataRepository.retrieveAllData(range)
                .stream()
                .filter(element -> element % 2 == 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> findOddNumber(int range){
        return this.dataRepository.retrieveAllData(range)
                .stream()
                .filter(element -> element % 2 != 0)
                .collect(Collectors.toList());
    }

}
