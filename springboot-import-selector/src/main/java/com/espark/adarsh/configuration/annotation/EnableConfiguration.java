package com.espark.adarsh.configuration.annotation;

import com.espark.adarsh.configuration.util.ApplicationImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ApplicationImportSelector.class)
public @interface EnableConfiguration {
    String value() default "";
}