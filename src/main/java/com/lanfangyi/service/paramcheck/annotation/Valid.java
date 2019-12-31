package com.lanfangyi.service.paramcheck.annotation;

import com.lanfangyi.service.paramcheck.aop.validate.ErrorLevelEnum;

import java.lang.annotation.*;

/**
 * 开启校验的标志注解，当一个方法上加了这个注解，就会开启校验。
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Valid {

    /**
     * 当校验出现错误的时候，是否记日志。
     */
    boolean addErrLog() default false;

    /**
     * 如果addErrLog 为true， 则记日志，此变量是日志的级别
     */
    ErrorLevelEnum errLogLevel() default ErrorLevelEnum.INFO;

    /**
     * 添加方法访问日志，记录方法入参和方法出参
     */
    boolean addMethodLog() default false;

    /**
     * 方法访问日志的级别
     */
    ErrorLevelEnum methodLogLevel() default ErrorLevelEnum.INFO;

    /**
     * 日志信息。如果为空，则所记信息为校验不通过的信息。
     */
    String logMsg() default "";

    /**
     * 是否将错误信息封装到RpcResponse的code、msg字段中去
     */
    boolean setCodeAndMsg() default true;

}
