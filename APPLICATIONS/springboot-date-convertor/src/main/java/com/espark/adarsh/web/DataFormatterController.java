package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;


@RestController
public class DataFormatterController {

    private final String yyyyMMdd = "yyyy-MM-dd";
    private final String ddMMyyyy = "dd.MM.yyyy";
    private final String MMddYY = "MM-dd-YY";


    Predicate<String> notNull = dateString -> dateString != null;
    Predicate<String> notEmpty = dateString -> !dateString.isEmpty();

    Predicate<String> yyyyMMddFormatPredicate = dateFormat -> dateFormat.equals(yyyyMMdd);
    Predicate<String> ddMMyyyyFormatPredicate = dateFormat -> dateFormat.equals(ddMMyyyy);
    Predicate<String> MMddYYFormatPredicate = dateFormat -> dateFormat.equals(MMddYY);

    BiFunction<String,String ,LocalDate> localDateBiFunction=new BiFunction<String, String, LocalDate>() {
        @Override
        public LocalDate apply(String dateString, String dateFormat) {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat));
        }
    };


    final Map<Predicate<String>, BiFunction<String,String, LocalDate>> conditions = new HashMap<>() {
        {
            put(yyyyMMddFormatPredicate, localDateBiFunction);
            put(ddMMyyyyFormatPredicate, localDateBiFunction);
            put(MMddYYFormatPredicate, localDateBiFunction);
        }
    };


    @GetMapping("/date/{dateString}/format/{dateFormat}")
    public String getFormattedDate(@PathVariable("dateString") String dateString,
                                   @PathVariable("dateFormat") String dateFormat) {

        if (!(notNull.and(notEmpty).test(dateString))) {
            throw new RuntimeException("DateInputException null or empty date input ");
        }

        if (!(notNull.and(notEmpty).test(dateFormat))) {
            throw new RuntimeException("DateInputFormatException null or empty date input format");
        }

        LocalDate localDate = conditions.entrySet()
                .stream()
                .filter(predicateFunctionEntry -> predicateFunctionEntry.getKey().test(dateFormat))
                .map(predicateFunctionEntry -> predicateFunctionEntry.getValue().apply(dateString,dateFormat))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("supplied format is not supported "));
        return "CONVERTED DATE AFTER FORMATTING "+localDate.toString();
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return e.getMessage();
    }
}
