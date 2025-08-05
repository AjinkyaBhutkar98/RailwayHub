package com.ajinkyabhutkar.irctc.customannotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CouchValidator implements ConstraintValidator<CheckCouch,Integer> {


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Validating couches");
        if(value>100){
            return false;
        }else{
            return true;
        }
    }
}
