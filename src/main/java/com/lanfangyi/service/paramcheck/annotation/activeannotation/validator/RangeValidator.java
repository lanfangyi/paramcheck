package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Range;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;

import java.lang.annotation.Annotation;

public class RangeValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        ValidateResult validateResult = null;
        Range range = (Range) annotation;
        double max = range.max();
        double min = range.min();
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException();
        }
        if (!(min <= Double.valueOf(String.valueOf(param)) && max >= Double.valueOf(String.valueOf(param)))) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数超出数值范围");
        }
        return validateResult;
    }
}
