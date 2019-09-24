package com.lanfangyi.service.paramcheck.constants;


import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class ErrorCode {

    private ErrorCode() {
    }

    public static  final String PARAM_ERROR = "参数错误";

    public static  final ValidateResult PARAM_ERROR2 = new ValidateResult();

}
