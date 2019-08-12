package com.haodf.service.paramcheck.annotation.activeannotation;

import com.haodf.service.paramcheck.annotation.ValidateBy;
import com.haodf.service.paramcheck.annotation.activeannotation.validator.BetweenValidator;

import java.lang.annotation.*;

/**
 * 校验参数是否在某个范围内的注解，只能是整数
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = BetweenValidator.class)
public @interface Between {

    long min();

    long max();

}
