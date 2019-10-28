package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.RegExp;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import org.springframework.util.StringUtils;

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
        RegExp regExp = (RegExp) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(regExp.errorCode(), paramName);
        }
        ValidateResult validateResult = null;
        String paramStr = String.valueOf(param);
        if (!StringUtils.isEmpty(regExp.value()) && !Pattern.matches(regExp.value(), paramStr)) {
            validateResult = ValidateResult.error(regExp.errorCode(), paramName + "参数不符合正则");
        }
        return validateResult;
    }
}
