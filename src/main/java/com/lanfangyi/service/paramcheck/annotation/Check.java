package com.lanfangyi.service.paramcheck.annotation;

import java.lang.annotation.*;

/**
 * 有的时候我们的入参是一个实体类，加了这个注解，表明这个实体类的属性需要校验。
 */
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Check {
}
