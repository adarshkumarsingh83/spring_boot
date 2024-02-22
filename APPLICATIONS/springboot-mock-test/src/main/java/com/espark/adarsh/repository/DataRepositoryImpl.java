package com.espark.adarsh.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class DataRepositoryImpl implements DataRepository {

    @Override
    public List<Integer> retrieveAllData(int range) {
        if (range <= 0) {
            throw new IllegalArgumentException(range+"");
        } else {
            return IntStream.range(0, range).boxed().collect(Collectors.toList());
        }
    }
}
