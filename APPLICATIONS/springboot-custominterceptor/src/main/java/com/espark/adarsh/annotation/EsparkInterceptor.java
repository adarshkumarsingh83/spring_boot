package com.espark.adarsh.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Component
public @interface EsparkInterceptor {

    String[] pathPatterns() default {};
    String[] excludePathPatterns() default {};

}
