package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.MinValidator;

import java.lang.annotation.*;

/**
 * 校验最小值的注解
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = MinValidator.class)
public @interface Min {

    double value();

}
