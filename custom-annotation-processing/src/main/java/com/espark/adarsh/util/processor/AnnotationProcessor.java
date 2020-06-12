package com.espark.adarsh.util.processor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface AnnotationProcessor {

    default List<String> getLowerEsparkService() {
        return new LinkedList<>();
    }

    default List<String> getUpperEsparkService() {
         return new LinkedList<>();
    }

    default Map<String, String> getMap() {
          return new HashMap<>();
    }
}
