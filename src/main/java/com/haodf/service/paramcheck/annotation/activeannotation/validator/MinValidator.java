package com.haodf.service.paramcheck.annotation.activeannotation.validator;

import com.haodf.service.paramcheck.annotation.activeannotation.Min;
import com.haodf.service.paramcheck.aop.validate.ValidateResult;
import com.haodf.service.paramcheck.aop.validate.Validateable;
import com.haodf.service.paramcheck.exception.AnnotationNoMatchFieldException;

import java.lang.annotation.Annotation;

public class MinValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        ValidateResult validateResult = null;
        Min min = (Min) annotation;
        double minValue = min.value();
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException();
        }
        if (minValue > Double.valueOf(String.valueOf(param))) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数小于最小值");
        }
        return validateResult;
    }
}
