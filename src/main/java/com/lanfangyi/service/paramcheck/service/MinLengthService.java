package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.MinLength;
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
@RequestMapping("/minLength")
public class MinLengthService {

    @GetMapping("/test")
    @Valid(addErrLog = true, errLogLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<Integer> test(@MinLength(2) int a, @MinLength(3) Long b, @MinLength(3) String aa) {
        System.out.println(a);
        return BaseResponse.success(a);
    }
}
