package com.lanfangyi.service.paramcheck.annotation;

import com.lanfangyi.service.paramcheck.constants.HttpErrorCode;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 所有校验注解的父注解，用于定义错误码
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ErrorCode {

    int errorCode() default HttpErrorCode.ACCESS_DENIED;

}
