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
            validateResult.setValidMsg(paramName + "参数不能为空集合");
        }
        NotEmpty notEmpty = (NotEmpty) annotation;
        if (((Collection) param).size() < notEmpty.minSize()) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数大小不能小于" + notEmpty.minSize());
        }
        if (((Collection) param).size() > notEmpty.maxSize()) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数大小不能大于" + notEmpty.maxSize());
        }
        return validateResult;
    }
}
