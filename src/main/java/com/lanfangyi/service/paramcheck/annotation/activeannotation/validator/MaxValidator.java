package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Max;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;

import java.lang.annotation.Annotation;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class MaxValidator implements Validateable {

    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        Max max = (Max) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(max.errorCode(), paramName);
        }
        ValidateResult validateResult = null;
        double maxValue = max.value();
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number");
        }
        if (maxValue < Double.valueOf(String.valueOf(param))) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数大于最大值");
        }
        return validateResult;
    }
}
