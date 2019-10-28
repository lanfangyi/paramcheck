package com.lanfangyi.service.paramcheck.aop.validate;

import java.lang.annotation.Annotation;

/**
 * 所有校验器都必须实现此接口，否则无法使用校验注解
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public interface Validateable {

    /**
     * 校验参数
     *
     * @param annotation 注解类型
     * @param param      要检验的参数
     * @param paramName  参数的名字
     * @return 校验结果
     */
    ValidateResult valid(Annotation annotation, Object param, String paramName);

}
