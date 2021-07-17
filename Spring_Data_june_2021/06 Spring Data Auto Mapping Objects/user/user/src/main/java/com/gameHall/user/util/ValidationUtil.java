package com.gameHall.user.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {
    <T> boolean isValid(T entity);
    <T>Set<ConstraintViolation<T>> violation(T entity);
}
