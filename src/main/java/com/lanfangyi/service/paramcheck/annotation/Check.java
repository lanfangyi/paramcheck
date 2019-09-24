package com.lanfangyi.service.paramcheck.annotation;

import java.lang.annotation.*;

/**
 * 有的时候我们的入参是一个实体类，加了这个注解，表明这个实体类的属性需要校验。
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Check {
}
