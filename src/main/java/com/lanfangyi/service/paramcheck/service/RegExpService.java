package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Range;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.RegExp;
import com.lanfangyi.service.paramcheck.aop.validate.ErrorLevelEnum;
import com.lanfangyi.service.paramcheck.resp.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/24 2:11 PM
 */
@RestController
@RequestMapping("/regexp")
public class RegExpService {

    /**
     * 匹配正整数
     */
    @GetMapping("/test")
    @Valid(addErrLog = true, errLogLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<Integer> test(@RegExp("^[1-9]\\d*$") Integer a) {
        System.out.println(a);
        return BaseResponse.success(a);
    }

    /**
     * 匹配正浮点数
     */
    @GetMapping("/test2")
    @Valid(addErrLog = true, errLogLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<Double> test2(@RegExp("^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$") double a) {
        System.out.println(a);
        return BaseResponse.success(a);
    }
}
