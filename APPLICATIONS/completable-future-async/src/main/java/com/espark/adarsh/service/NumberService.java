package com.espark.adarsh.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class NumberService {
    public List<Object> get(Integer number){
        System.out.println(Thread.currentThread().getName()+" limit :=> "+number);
        return IntStream.range(0,number+1).boxed().collect(Collectors.toList());
    }
}
