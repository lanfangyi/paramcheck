package com.haodf.service.paramcheck.annotation.activeannotation;

import com.haodf.service.paramcheck.annotation.ValidateBy;
import com.haodf.service.paramcheck.annotation.activeannotation.validator.AmongValidator;

import java.lang.annotation.*;

/**
 * 校验参数是否为某几个值之中的一个的注解
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = AmongValidator.class)
public @interface Among {

    double[] value();
}
