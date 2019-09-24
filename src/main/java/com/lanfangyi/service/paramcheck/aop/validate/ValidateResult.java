package com.lanfangyi.service.paramcheck.aop.validate;

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

    public static ValidateResult nullValiddateResult(String paramName){
        ValidateResult validateResult = new ValidateResult();
        validateResult.setCode(405);
        validateResult.setValidMsg(paramName + "参数为null");
        return validateResult;
    }

}
