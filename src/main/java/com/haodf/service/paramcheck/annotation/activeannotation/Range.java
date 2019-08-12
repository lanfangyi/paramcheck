package com.haodf.service.paramcheck.annotation.activeannotation;

import com.haodf.service.paramcheck.annotation.ValidateBy;
import com.haodf.service.paramcheck.annotation.activeannotation.validator.RangeValidator;

import java.lang.annotation.*;

/**
 *校验参数是否是在某一段数值区间的注解，可以为小数
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = RangeValidator.class)
public @interface Range {

    double min();

    double max();
}
