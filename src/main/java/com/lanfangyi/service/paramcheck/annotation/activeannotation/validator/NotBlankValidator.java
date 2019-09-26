package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class NotBlankValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        if (null == param) {
            return ValidateResult.nullValidateResult(paramName);
        }
        ValidateResult validateResult;
        if (!(param instanceof CharSequence)) {
            throw new AnnotationNoMatchFieldException("Class of param is not CharSequence");
        }
        NotBlank notBlank = (NotBlank) annotation;
        if (StringUtils.isEmpty(param)) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数为空字符串");
            return validateResult;
        }
        if (!StringUtils.isEmpty(notBlank.startWith()) && !param.toString().startsWith(notBlank.startWith())) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数不以" + notBlank.startWith() + "开头");
            return validateResult;
        }
        if (!StringUtils.isEmpty(notBlank.endWith()) && !param.toString().endsWith(notBlank.endWith())) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数不以" + notBlank.endWith() + "结尾");
            return validateResult;
        }
        if (!StringUtils.isEmpty(notBlank.contain()) && !param.toString().contains(notBlank.contain())) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数不包含" + notBlank.endWith());
            return validateResult;
        }
        if (!StringUtils.isEmpty(notBlank.contain()) && param.toString().length() < notBlank.minLength()) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数长度不能小于" + notBlank.minLength());
            return validateResult;
        }
        if (!StringUtils.isEmpty(notBlank.contain()) && notBlank.maxLength() != -1 && param.toString().length() > notBlank.maxLength()) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数长度不能大于" + notBlank.maxLength());
            return validateResult;
        }
        if (notBlank.among().length != 0 && !CollectionUtils.isEmpty(Arrays.asList(notBlank.among()))
            && !(notBlank.among().length == 1 && StringUtils.isEmpty(notBlank.among()[0]))) {
            String[] amongStr = notBlank.among();
            if (!Arrays.asList(amongStr).contains(param.toString())) {
                validateResult = new ValidateResult();
                validateResult.setCode(405);
                validateResult.setValidMsg(paramName + "参数的取值范围是" + Arrays.toString(notBlank.among()));
                return validateResult;
            }
        }
        return null;
    }
}
