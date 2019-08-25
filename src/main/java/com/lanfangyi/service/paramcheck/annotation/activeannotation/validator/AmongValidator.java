package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Among;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class AmongValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        if (null == param) {
            return ValidateResult.nullValiddateResult(paramName);
        }
        ValidateResult validateResult = null;
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number");
        }
        Among among = (Among) annotation;
        double[] value = among.value();
        List<Double> list = new ArrayList<>();
        for (double v : value) {
            list.add(v);
        }

        if (!CollectionUtils.isEmpty(list) && !list.contains(param)) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数为空字符串");
        }
        return validateResult;
    }
}
