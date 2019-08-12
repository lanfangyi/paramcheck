package com.haodf.service.paramcheck.annotation.activeannotation.validator;

import com.haodf.service.paramcheck.aop.validate.ValidateResult;
import com.haodf.service.paramcheck.aop.validate.Validateable;
import com.haodf.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.Collection;

public class NotEmptyValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        ValidateResult validateResult = null;
        if (!(param instanceof Collection)) {
            throw new AnnotationNoMatchFieldException();
        }
        if (CollectionUtils.isEmpty((Collection) param)) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数为空集合");
        }
        return validateResult;
    }
}
