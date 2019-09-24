package com.lanfangyi.service.paramcheck.aop.validate;

import java.lang.annotation.Annotation;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public interface Validateable {

    ValidateResult valid(Annotation annotation, Object param, String paramName);

}
