package com.lanfangyi.service.paramcheck.annotation.activeannotation;

import com.lanfangyi.service.paramcheck.annotation.ErrorCode;
import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.validator.NotBlankValidator;
import com.lanfangyi.service.paramcheck.constants.HttpErrorCode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


/**
 * 校验参数类型为CharSequence的参数是否为空的注解
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@ErrorCode
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidateBy(validatedClass = NotBlankValidator.class)
public @interface NotBlank {

    /**
     * 必须以某字符串开头
     */
    String startWith() default "";

    /**
     * 必须以某字符串结尾
     */
    String endWith() default "";

    /**
     * 必须包含某字符串
     */
    String contain() default "";

    /**
     * 必须是几个字符串中的某个
     */
    String[] among() default "";

    /**
     * 字符串的最小长度
     */
    int minLength() default Integer.MIN_VALUE;

    /**
     * 字符串的最大长度
     */
    int maxLength() default Integer.MAX_VALUE;

    @AliasFor(annotation = ErrorCode.class)
    int errorCode() default HttpErrorCode.ACCESS_DENIED;
}
