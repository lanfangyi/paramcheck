package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotEmpty;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class NotEmptyValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        NotEmpty notEmpty = (NotEmpty) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(notEmpty.errorCode(), paramName);
        }
        if (!(param instanceof Collection)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Collection");
        }
        if (CollectionUtils.isEmpty((Collection) param)) {
            return ValidateResult.error(notEmpty.errorCode(), paramName + "参数不能为空集合");
        }

        if (((Collection) param).size() < notEmpty.minSize()) {
            return ValidateResult.error(notEmpty.errorCode(), paramName + "参数size不能小于" + notEmpty.minSize());
        }
        if (((Collection) param).size() > notEmpty.maxSize()) {
            return ValidateResult.error(notEmpty.errorCode(), paramName + "参数size不能大于" + notEmpty.maxSize());
        }
        return null;
    }
}
