package com.haodf.service.paramcheck.constants;


import com.haodf.service.paramcheck.aop.validate.ValidateResult;

public class ErrorCode {

    private ErrorCode() {
    }

    public static  final String PARAM_ERROR = "参数错误";

    public static  final ValidateResult PARAM_ERROR2 = new ValidateResult();

}
