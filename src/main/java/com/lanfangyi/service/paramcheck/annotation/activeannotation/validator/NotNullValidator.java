package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;

import java.lang.annotation.Annotation;

public class NotNullValidator implements Validateable {

    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        if (param == null) {
            return ValidateResult.nullValiddateResult(paramName);
        }
        return null;
    }
}
