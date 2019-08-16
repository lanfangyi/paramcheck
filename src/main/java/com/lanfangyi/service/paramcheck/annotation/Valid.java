package com.lanfangyi.service.paramcheck.annotation;

import com.lanfangyi.service.paramcheck.aop.validate.ErrorLevelEnum;

import java.lang.annotation.*;

/**
 * 开启校验的标志注解，当一个方法上加了这个注解，就会开启校验。
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
     * 当校验不通过的时候，接口的返回值，这个值要定义在某个产量文件中。定义之后，程序会去读取并识别类型是否与接口
     */
    String msgClassStaticField() default "";

    /**
     * 当校验出现错误的时候，是否记日志。
     */
    boolean addErrLog() default false;

    /**
     * 如果addErrLog 为true， 则记日志，此变量是日志的级别
     */
    ErrorLevelEnum logLevel() default ErrorLevelEnum.INFO;

    /**
     * 日志信息
     */
    String logMsg() default "";

}
