package com.devdive.backend.common;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


public class SelfValidating<T> {
    private Validator validator;

    public SelfValidating(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validateSelf(){
        var violations = validator.validate((T) this);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
