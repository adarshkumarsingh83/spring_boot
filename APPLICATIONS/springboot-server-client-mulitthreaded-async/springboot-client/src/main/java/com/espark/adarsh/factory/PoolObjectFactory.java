package com.espark.adarsh.factory;

import com.espark.adarsh.bean.PoolObject;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@Slf4j
public class PoolObjectFactory<T extends PoolObject> {

    private final Integer poolSize ;
    private final BlockingDeque<T> pool;

    public PoolObjectFactory(Integer poolSize,
                            Supplier<T> objectFactory) {
        this.poolSize = poolSize;
        this.pool = new LinkedBlockingDeque<>(poolSize);
        IntStream.range(0, this.poolSize)
                .forEach(i -> pool.add(objectFactory.get()));
    }

    public T getObject() throws InterruptedException {
        return this.pool.take();
    }

    public void returnObject(T object) {
        if(object!=null) {
            log.info("Returning object to pool: {}", object);
            object.reset();
            this.pool.offer(object);
        }
    }

}
