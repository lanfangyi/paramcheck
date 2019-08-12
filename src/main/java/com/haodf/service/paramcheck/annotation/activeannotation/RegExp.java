package com.haodf.service.paramcheck.annotation.activeannotation;

import com.haodf.service.paramcheck.annotation.ValidateBy;
import com.haodf.service.paramcheck.annotation.activeannotation.validator.RegExpValidator;

import java.lang.annotation.*;

/**
 * 自定义正则表达式的注解。参数必须符合正则表达式。
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = RegExpValidator.class)
public @interface RegExp {

    String value();
}
