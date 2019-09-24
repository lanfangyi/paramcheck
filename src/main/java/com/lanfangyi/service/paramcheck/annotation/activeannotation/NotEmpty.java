package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.NotEmptyValidator;

import java.lang.annotation.*;

/**
 * 校验类型为集合的参数是否为空的注解
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = NotEmptyValidator.class)
public @interface NotEmpty {

    int minSize() default -1;

    int maxSize() default -1;
}
