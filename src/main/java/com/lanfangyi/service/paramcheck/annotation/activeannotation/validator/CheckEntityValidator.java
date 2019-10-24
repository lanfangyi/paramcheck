package com.lanfangyi.service.paramcheck.annotation.activeannotation.validator;

import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

/**
 * 此校验器只是一个标志，无实际作用。
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Slf4j
public class CheckEntityValidator implements Validateable {

    @Override
    public ValidateResult valid(Annotation annotation, Object param, String paramName) {
        log.info("CheckEntityValidator valid.");
        return null;
    }
}
