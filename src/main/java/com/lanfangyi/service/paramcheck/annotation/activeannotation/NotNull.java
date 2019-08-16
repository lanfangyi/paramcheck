package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.NotNullValidator;

import java.lang.annotation.*;

/**
 * 校验参数是否为null的注解
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = NotNullValidator.class)
public @interface NotNull {
}
