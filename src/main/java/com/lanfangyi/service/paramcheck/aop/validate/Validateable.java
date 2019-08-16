package com.lanfangyi.service.paramcheck.aop.validate;

import java.lang.annotation.Annotation;

public interface Validateable {

    ValidateResult valid(Annotation annotation, Object param, String paramName);

}
