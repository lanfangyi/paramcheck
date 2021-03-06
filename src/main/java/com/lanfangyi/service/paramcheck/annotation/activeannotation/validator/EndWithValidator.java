package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.EndWith;
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
public class EndWithValidator implements Validateable {

    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        EndWith endWith = (EndWith) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(endWith.errorCode(), paramName);
        }
        ValidateResult validateResult = null;
        String end = endWith.value();
        if (!(param instanceof Number || param instanceof CharSequence)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number and not CharSequence");
        }
        if (!StringUtils.isEmpty(end) && !String.valueOf(param).endsWith(end)) {
            validateResult = ValidateResult.error(endWith.errorCode(), paramName + "参数必须以" + end + "结尾");
        }
        return validateResult;
    }
}
