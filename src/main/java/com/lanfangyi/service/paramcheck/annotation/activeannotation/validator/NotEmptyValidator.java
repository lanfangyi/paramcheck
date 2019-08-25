package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.Collection;

public class NotEmptyValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        if (null == param) {
            return ValidateResult.nullValiddateResult(paramName);
        }
        ValidateResult validateResult = null;
        if (!(param instanceof Collection)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Collection");
        }
        if (CollectionUtils.isEmpty((Collection) param)) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数为空集合");
        }
        return validateResult;
    }
}
