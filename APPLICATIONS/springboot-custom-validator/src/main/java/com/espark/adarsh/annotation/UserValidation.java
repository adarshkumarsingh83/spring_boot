package com.espark.adarsh.annotation;


import com.espark.adarsh.validator.UserValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserValidation {
    String message() default "{error.user}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
