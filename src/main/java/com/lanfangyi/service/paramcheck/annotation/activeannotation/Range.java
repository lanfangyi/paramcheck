package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ErrorCode;
import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.RangeValidator;
import com.lanfangyi.service.paramcheck.constants.HttpErrorCode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 校验参数是否是在某一段数值区间的注解，可以为小数。可用于类型是整数或小数的参数。
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = RangeValidator.class)
@ErrorCode
public @interface Range {

    double min() default Double.MIN_VALUE;

    double max() default Double.MAX_VALUE;

    @AliasFor(annotation = ErrorCode.class)
    int errorCode() default HttpErrorCode.ACCESS_DENIED;
}
