package com.company.interview.employeetask.custom.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class IdValidator implements ConstraintValidator<ValidId, Long> {

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(id) && id > 0;
    }
}