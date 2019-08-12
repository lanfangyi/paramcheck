package com.haodf.service.paramcheck.annotation.activeannotation.validator;

import com.haodf.service.paramcheck.annotation.activeannotation.Max;
import com.haodf.service.paramcheck.aop.validate.ValidateResult;
import com.haodf.service.paramcheck.aop.validate.Validateable;
import com.haodf.service.paramcheck.exception.AnnotationNoMatchFieldException;

import java.lang.annotation.Annotation;

public class MaxValidator implements Validateable {

    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        ValidateResult validateResult = null;
        Max max = (Max) annotation;
        double maxValue = max.value();
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException();
        }
        if (maxValue < Double.valueOf(String.valueOf(param))) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数大于最大值");
        }
        return validateResult;
    }
}