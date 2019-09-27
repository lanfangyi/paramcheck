package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Range;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;

import java.lang.annotation.Annotation;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class RangeValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        Range range = (Range) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(range.errorCode(), paramName);
        }
        ValidateResult validateResult = null;
        double max = range.max();
        double min = range.min();
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number");
        }
        if (!(min <= Double.valueOf(String.valueOf(param)) && max >= Double.valueOf(String.valueOf(param)))) {
            validateResult = ValidateResult.error(range.errorCode(), paramName + "参数超出数值范围");
        }
        return validateResult;
    }
}
