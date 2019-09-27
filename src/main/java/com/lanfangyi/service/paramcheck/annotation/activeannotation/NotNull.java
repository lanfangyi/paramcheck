package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ErrorCode;
import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.NotNullValidator;
import com.lanfangyi.service.paramcheck.constants.HttpErrorCode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 校验参数是否为null的注解
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = NotNullValidator.class)
@ErrorCode
public @interface NotNull {

    @AliasFor(annotation = ErrorCode.class)
    int errorCode() default HttpErrorCode.ACCESS_DENIED;
}
