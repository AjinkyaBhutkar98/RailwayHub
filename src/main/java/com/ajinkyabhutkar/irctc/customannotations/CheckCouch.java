package com.ajinkyabhutkar.irctc.customannotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CouchValidator.class)
public @interface CheckCouch {

    String message() default "invalid couch number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};

}
