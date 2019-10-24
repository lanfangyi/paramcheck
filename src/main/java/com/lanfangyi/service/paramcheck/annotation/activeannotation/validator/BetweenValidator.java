package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Between;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import com.lanfangyi.service.paramcheck.exception.NoRationalNumberException;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class BetweenValidator implements Validateable {

    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        Between between = (Between) annotation;
        if (null == param) {
            return ValidateResult.nullValidateResult(between.errorCode(), paramName);
        }
        ValidateResult validateResult = null;
        long max = between.max();
        long min = between.min();
        if (!(param instanceof Number)) {
            throw new AnnotationNoMatchFieldException("Class of param is not Number");
        }
        if (!isRationalNumber(String.valueOf(param))) {
            throw new NoRationalNumberException();
        }
        if (!(min <= Long.valueOf(String.valueOf(param)) && max >= Long.valueOf(String.valueOf(param)))) {
            validateResult = ValidateResult.error(between.errorCode(), paramName + "参数超出数值范围");
        }
        return validateResult;
    }

    private static boolean isMatch(String regex, String orginal) {
        if (StringUtils.isEmpty(orginal) || StringUtils.isEmpty(orginal.trim())) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }


    private static boolean isRationalNumber(String orginal) {
        return isPositiveInteger(orginal) || isNegativeInteger(orginal);
    }

    /**
     * 校验正整数
     *
     * @param orginal 数字字符串
     * @return boolean
     */
    private static boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

    /**
     * 校验负整数
     *
     * @param orginal 数字字符串
     * @return boolean
     */
    private static boolean isNegativeInteger(String orginal) {
        return isMatch("^-[1-9]\\d*", orginal);
    }


}
