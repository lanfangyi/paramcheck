package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Among;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

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
        double[] value = among.value();
        List<Double> list = new ArrayList<>();
        for (double v : value) {
            list.add(v);
        }

        if (!CollectionUtils.isEmpty(list) && !list.contains(Double.valueOf(String.valueOf(param)))) {
            validateResult = new ValidateResult();
            validateResult.setCode(among.errorCode());
            validateResult.setValidMsg(paramName + "参数不在给定的数组里！");
        }
        return validateResult;
    }
}
