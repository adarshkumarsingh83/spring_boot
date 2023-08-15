package com.example.demo.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@RestController
public class DataConversionController {

    final Map<Predicate<String>, Function<String, String>> conditions = new HashMap<Predicate<String>, Function<String, String>>();

    Predicate<String> nullCheck = data -> data != null;
    Predicate<String> emptyCheck = data -> !(data.trim().isEmpty());
    Predicate<String> checkLowerCase = data -> data.equals("LOWER");
    Predicate<String> checkUpperCase = data -> data.equals("UPPER");


    Function<String, String> toLowerCase = new Function<String, String>() {
        @Override
        public String apply(String data) {
            return data.toLowerCase();
        }
    };
    Function<String, String> toUpperCase = new Function<String, String>() {
        @Override
        public String apply(String data) {
            return data.toUpperCase();
        }
    };

    Supplier<String> nullOrEmptyInput = new Supplier<String>() {
        @Override
        public String get() {
            return "input type null or empty or not uppper or lower ";
        }
    };

    {
        conditions.put(checkLowerCase, toLowerCase);
        conditions.put(checkUpperCase, toUpperCase);
    }


    @GetMapping("/convert/{data}/{type}")
    public String getData(@PathVariable("data") Optional<String> data, @PathVariable("type") Optional<String> type) throws Exception {

        if(!(nullCheck.and(emptyCheck).test(data.get()))){
            throw new Exception("empty or null input ");
        }


        return "welcome " + conditions.entrySet()
                .stream()
                .filter(predicateFunctionEntry -> predicateFunctionEntry.getKey().test(type.get()))
                .map(predicateFunctionEntry -> predicateFunctionEntry.getValue().apply(data.get()))
                .findFirst().orElseGet(nullOrEmptyInput);
    }


    @ExceptionHandler({ Exception.class })
    public String handleException() {
        return "input is null or empty ";
    }

}
