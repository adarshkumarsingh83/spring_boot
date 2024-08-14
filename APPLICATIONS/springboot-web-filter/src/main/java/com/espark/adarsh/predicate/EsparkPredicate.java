package com.espark.adarsh.predicate;

@FunctionalInterface
public interface EsparkPredicate <T1,T2,T3>{

    boolean test(T1 t1,T2 t2 ,T3 t3);
}
