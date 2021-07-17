package com.gameHall.user.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {
    private final Validator validator;

    @Autowired
    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }



    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> violation(T entity) {
        return validator.validate(entity);
    }
}
