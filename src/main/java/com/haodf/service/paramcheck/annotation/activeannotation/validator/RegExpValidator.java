package com.haodf.service.paramcheck.annotation.activeannotation.validator;

import com.haodf.service.paramcheck.annotation.activeannotation.RegExp;
import com.haodf.service.paramcheck.aop.validate.ValidateResult;
import com.haodf.service.paramcheck.aop.validate.Validateable;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

public class RegExpValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        ValidateResult validateResult = null;
        String s = String.valueOf(param);
        RegExp regExp = (RegExp) annotation;
        if (param == null || !Pattern.matches(regExp.value(), s)) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数不符合正则");
        }
        return validateResult;
    }
}
