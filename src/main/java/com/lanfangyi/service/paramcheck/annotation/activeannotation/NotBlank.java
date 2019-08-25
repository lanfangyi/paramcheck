package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.NotBlankValidator;

import java.lang.annotation.*;

/**
 * 校验参数类型为CharSequence的参数是否为空的注解
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = NotBlankValidator.class)
public @interface NotBlank {

    String startWith() default "";

    String endWith() default "";

    String contain() default "";

    String[] among() default "";
}
