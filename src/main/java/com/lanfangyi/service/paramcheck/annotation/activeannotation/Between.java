package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ErrorCode;
import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.BetweenValidator;
import com.lanfangyi.service.paramcheck.constants.HttpErrorCode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 校验参数是否在某个范围内的注解，只能是整数
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = BetweenValidator.class)
@ErrorCode
public @interface Between {

    long min() default Long.MIN_VALUE;

    long max() default Long.MAX_VALUE;

    @AliasFor(annotation = ErrorCode.class)
    int errorCode() default HttpErrorCode.ACCESS_DENIED;

}
