package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class NotBlankValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        if (null == param) {
            return ValidateResult.nullValiddateResult(paramName);
        }
        ValidateResult validateResult = null;
        if (!(param instanceof CharSequence)) {
            throw new AnnotationNoMatchFieldException("Class of param is not CharSequence");
        }
        NotBlank notBlank = (NotBlank) annotation;
        boolean startWith = true;
        boolean endWith = true;
        boolean contain = true;
        boolean among = true;
        if (!StringUtils.isEmpty(notBlank.startWith())) {
            startWith = param.toString().startsWith(notBlank.startWith());
        }
        if (!StringUtils.isEmpty(notBlank.endWith())) {
            endWith = param.toString().endsWith(notBlank.endWith());
        }
        if (!StringUtils.isEmpty(notBlank.contain())) {
            contain = param.toString().contains(notBlank.contain());
        }
        if (notBlank.among().length != 0 && !CollectionUtils.isEmpty(Arrays.asList(notBlank.among()))) {
            String[] amongStr = notBlank.among();
            among = Arrays.asList(amongStr).contains(param.toString());
        }
        if (StringUtils.isEmpty(param) || !startWith || !endWith || !contain || !among) {
            validateResult = new ValidateResult();
            validateResult.setCode(405);
            validateResult.setValidMsg(paramName + "参数为空字符串");
        }
        return validateResult;
    }
}
