package com.lanfangyi.service.paramcheck.annotation;

import com.lanfangyi.service.paramcheck.aop.validate.Validateable;

import java.lang.annotation.*;

/**
 * 定义校验器的注解，校验注解上加这个注解，用于定义相对应的注解校验器
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateBy {

    Class<? extends Validateable> validatedClass();

}
