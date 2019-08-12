package com.haodf.service.paramcheck.annotation.activeannotation;

import com.haodf.service.paramcheck.annotation.ValidateBy;
import com.haodf.service.paramcheck.annotation.activeannotation.validator.MaxValidator;

import java.lang.annotation.*;

/**
 * 校验最大值的注解
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = MaxValidator.class)
public @interface Max {

    double value();

}
