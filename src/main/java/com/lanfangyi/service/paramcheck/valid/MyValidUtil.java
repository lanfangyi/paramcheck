package com.lanfangyi.service.paramcheck.valid;

import com.lanfangyi.service.paramcheck.annotation.Valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class MyValidUtil implements ConstraintValidator<Valid, Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("========");
        return false;
    }
}
