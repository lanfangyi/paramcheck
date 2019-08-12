package com.haodf.service.paramcheck.annotation.activeannotation.validator;

import com.haodf.service.paramcheck.annotation.activeannotation.NotBlank;
import com.haodf.service.paramcheck.aop.validate.ValidateResult;
import com.haodf.service.paramcheck.aop.validate.Validateable;
import com.haodf.service.paramcheck.exception.AnnotationNoMatchFieldException;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class NotBlankValidator implements Validateable {
    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        ValidateResult validateResult = null;
        if (!(param instanceof CharSequence)) {
            throw new AnnotationNoMatchFieldException();
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
        if (notBlank.among().length != 0) {
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
