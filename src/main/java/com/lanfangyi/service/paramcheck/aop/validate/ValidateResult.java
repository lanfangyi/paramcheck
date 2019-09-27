package com.lanfangyi.service.paramcheck.aop.validate;

import com.lanfangyi.service.paramcheck.constants.HttpErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResult implements Serializable {

    private int code;

    private String validMsg;

    public static ValidateResult nullValidateResult(int code, String paramName){
        ValidateResult validateResult = new ValidateResult();
        validateResult.setCode(code);
        validateResult.setValidMsg(paramName + "参数不能为null");
        return validateResult;
    }

    public static ValidateResult error(String validMsg) {
        ValidateResult validateResult = new ValidateResult();
        validateResult.setCode(HttpErrorCode.ACCESS_DENIED);
        validateResult.setValidMsg(validMsg);
        return validateResult;
    }

    public static ValidateResult error(int code, String validMsg) {
        ValidateResult validateResult = new ValidateResult();
        validateResult.setCode(code);
        validateResult.setValidMsg(validMsg);
        return validateResult;
    }

}
