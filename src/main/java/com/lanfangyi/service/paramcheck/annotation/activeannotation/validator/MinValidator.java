package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;

import java.lang.annotation.Annotation;

public class MinValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        if (null == param) {
            return ValidateResult.nullValiddateResult(paramName);
        }
        ValidateResult validateResult = null;
        Min min = (Min) annotation;
        double minValue = min.value();
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number");
        }
        if (minValue > Double.valueOf(String.valueOf(param))) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数小于最小值");
        }
        return validateResult;
    }
}
