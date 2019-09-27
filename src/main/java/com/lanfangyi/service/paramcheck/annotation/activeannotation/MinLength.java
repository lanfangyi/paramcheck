package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ErrorCode;
import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.MinLengthValidator;
import com.lanfangyi.service.paramcheck.constants.HttpErrorCode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验最小值的注解
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = MinLengthValidator.class)
@ErrorCode
public @interface MinLength {

    int value() default Integer.MIN_VALUE;

    @AliasFor(annotation = ErrorCode.class)
    int errorCode() default HttpErrorCode.ACCESS_DENIED;

}
