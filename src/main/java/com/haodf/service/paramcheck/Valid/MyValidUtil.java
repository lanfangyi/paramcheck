package com.haodf.service.paramcheck.Valid;

import com.haodf.service.paramcheck.annotation.Valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyValidUtil implements ConstraintValidator<Valid, Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("========");
        return false;
    }
}
