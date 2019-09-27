package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.MinLength;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;

import java.lang.annotation.Annotation;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class MinLengthValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        MinLength minLength = (MinLength) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(minLength.errorCode(), paramName);
        }
        ValidateResult validateResult = null;
        int mLength = minLength.value();
        if (!(param instanceof Number || param instanceof CharSequence)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number and not CharSequence");
        }
        if (mLength > String.valueOf(param).length()) {
            validateResult = ValidateResult.error(minLength.errorCode(), paramName + "参数长度小于规定最小长度！");
        }
        return validateResult;
    }
}
