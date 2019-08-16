package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;

import java.lang.annotation.Annotation;

public class NotNullValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        ValidateResult validateResult = null;
        if (param == null) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数为空");
        }
        return validateResult;
    }
}
