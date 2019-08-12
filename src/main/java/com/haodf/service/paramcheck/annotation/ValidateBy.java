package com.haodf.service.paramcheck.annotation;

import com.haodf.service.paramcheck.aop.validate.Validateable;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateBy {

    String message() default "";

    Class<? extends Validateable> validatedClass();

}
