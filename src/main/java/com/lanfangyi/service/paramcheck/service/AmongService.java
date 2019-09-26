package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Among;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Max;
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
@RequestMapping("/among")
public class AmongService {

    @GetMapping("/test")
    @Valid(addErrLog = true, logLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<Integer> test(@Among({1, 2}) @Max(3) int a) {
        System.out.println(a);
        return BaseResponse.success(a);
    }

    @GetMapping("/test2")
    @Valid
    public BaseResponse<Double> test2(@Among({1}) Double a) {
        System.out.println(a);
        return BaseResponse.success(a);
    }
}
