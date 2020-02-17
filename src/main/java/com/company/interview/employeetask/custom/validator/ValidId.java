package com.company.interview.employeetask.custom.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidId {
    String message() default "Entity ID can not be null or less.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}