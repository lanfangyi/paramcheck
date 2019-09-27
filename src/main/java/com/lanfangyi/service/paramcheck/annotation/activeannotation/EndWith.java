package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ErrorCode;
import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.EndWithValidator;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.MinLengthValidator;
import com.lanfangyi.service.paramcheck.constants.HttpErrorCode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验字符串或数字是否以xxx结尾的注解。用于字符串类型的参数。
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = EndWithValidator.class)
@ErrorCode
public @interface EndWith {

    String value() default "";

    @AliasFor(annotation = ErrorCode.class)
    int errorCode() default HttpErrorCode.ACCESS_DENIED;
}
