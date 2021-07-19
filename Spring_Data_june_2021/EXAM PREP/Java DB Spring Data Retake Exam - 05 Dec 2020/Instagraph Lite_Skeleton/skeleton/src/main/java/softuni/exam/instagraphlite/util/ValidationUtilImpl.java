package softuni.exam.instagraphlite.util;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

public class ValidationUtilImpl implements ValidatorUtil {
    private final Validator validator;

    @Autowired
    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).size() == 0;
    }
}
