package com.espark.adarsh.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ApplicationServiceMain {

    @Autowired
    private NumberService service;

    static Map<String,CompletableFuture<List<Object>>> futureMap=new HashMap();

    public List<List<Object>>  getResponse() {
        List<List<Object>> listResponse =new LinkedList<>();
        List<Integer> number=IntStream.range(0,50).boxed().collect(Collectors.toList());

        System.out.println(Thread.currentThread().getName()+" array limit "+number);

        for(int x: number){
            futureMap.put(x+"-", CompletableFuture.supplyAsync(() -> service.get(x)));
        }

        futureMap.forEach((k,v)->{
            try {
                List<Object> list=null;
                if(v.isDone()){
                     list = v.get();
                }else{
                    list= v.getNow(Arrays.asList());
                }
                System.out.println(Thread.currentThread().getName()+" "+list);
                listResponse.add(list);
            }catch(ExecutionException | InterruptedException rethrow) {
                throw new RuntimeException(rethrow);
            }
        });
        return listResponse;
    }

}
