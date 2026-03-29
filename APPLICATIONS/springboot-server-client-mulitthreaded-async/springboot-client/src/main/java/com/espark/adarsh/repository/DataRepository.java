package com.espark.adarsh.repository;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.factory.PoolObjectFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class DataRepository {

    private static final Random random = new Random();

    private PoolObjectFactory<User> userPoolObjectFactory;

    public DataRepository(PoolObjectFactory<User> userPoolObjectFactory) {
        this.userPoolObjectFactory = userPoolObjectFactory;
    }

    public  List<User>  generateData(String batch ,int size){
        List<User> data =  IntStream.range(1,size)
                .mapToObj(i ->{
                    User user = null;
                    try {
                        user = userPoolObjectFactory.getObject();
                        user.setName("adarsh"+batch+i);
                        user.setEmail("adarsh"+batch+i+"@espark.com");
                    } catch (InterruptedException e) {
                       log.error(e.getMessage());
                    }
                    return user;
                }
         ).collect(Collectors.toList());



        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
           log.error("exception {}",e.getMessage());
        }
        return data;
    }
}
