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
     * 当出错的时候，返回值所在类
     */
    Class msgClass() default Valid.class;

    /**
     * 当校验不通过的时候，接口的返回值，这个值要定义在某个常量文件中。定义之后，程序会去读取并识别类型是否与接口的返回值类型想匹配
     */
    String msgClassStaticField() default "";

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
     * 当返回值类型是BaseResponse或其子类使，默认会把错误信息封装到code、message字段中去。
     */
    boolean setCodeAndMsg() default true;

}
