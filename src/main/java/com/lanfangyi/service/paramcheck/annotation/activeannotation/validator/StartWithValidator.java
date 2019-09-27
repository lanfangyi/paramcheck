package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.ErrorCode;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.StartWith;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class StartWithValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        StartWith startWith = (StartWith) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(startWith.errorCode(), paramName);
        }
        ValidateResult validateResult = null;

        String start = startWith.value();
        if (!(param instanceof Number || param instanceof CharSequence)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number and not CharSequence");
        }
        if (!StringUtils.isEmpty(start) && !String.valueOf(param).startsWith(start)) {
            validateResult = ValidateResult.error(startWith.errorCode(), paramName + "参数开头不符合要求");
        }
        return validateResult;
    }
}
