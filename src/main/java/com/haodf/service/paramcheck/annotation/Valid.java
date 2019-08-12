package com.haodf.service.paramcheck.annotation;

import com.haodf.service.paramcheck.aop.validate.ErrorLevelEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Valid {

    String message() default "";

    Class msgClass() default Valid.class;

    String msgClassStaticField() default "";

    boolean setErrMsg() default false;

    boolean addErrLog() default false;

    ErrorLevelEnum logLevel() default ErrorLevelEnum.INFO;

    String logMsg() default "";

}
