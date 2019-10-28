package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;

import java.lang.annotation.Annotation;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class MinValidator implements Validateable {

    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        Min min = (Min) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(min.errorCode(), paramName);
        }
        ValidateResult validateResult = null;
        double minValue = min.value();
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number");
        }
        if (minValue > Double.valueOf(String.valueOf(param))) {
            validateResult = ValidateResult.error(min.errorCode(), paramName + "参数小于最小值");
        }
        return validateResult;
    }
}
