package com.lanfangyi.service.paramcheck.aop.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResult implements Serializable {

    private int code;

    private String validMsg;

    public static ValidateResult nullValiddateResult(String paramName){
        ValidateResult validateResult = new ValidateResult();
        validateResult.setCode(405);
        validateResult.setValidMsg(paramName + "参数为null");
        return validateResult;
    }

}
