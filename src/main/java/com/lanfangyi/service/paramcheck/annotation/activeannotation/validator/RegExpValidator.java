package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.RegExp;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class RegExpValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        if (null == param) {
            return ValidateResult.nullValidateResult(paramName);
        }
        ValidateResult validateResult = null;
        String s = String.valueOf(param);
        RegExp regExp = (RegExp) annotation;
        if (!Pattern.matches(regExp.value(), s)) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数不符合正则");
        }
        return validateResult;
    }
}
