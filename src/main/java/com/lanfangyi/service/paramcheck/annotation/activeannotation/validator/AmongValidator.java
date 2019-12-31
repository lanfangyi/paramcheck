package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Among;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class AmongValidator implements Validateable {

    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        Among among = (Among) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(among.errorCode(), paramName);
        }
        ValidateResult validateResult = null;
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number");
        }
        List<Double> list = Arrays.stream(among.value()).boxed().collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list) && !list.contains(Double.valueOf(String.valueOf(param)))) {
            validateResult = ValidateResult.error(among.errorCode(), paramName + "参数不是可选值。可选值：" + list);
        }
        return validateResult;
    }
}
