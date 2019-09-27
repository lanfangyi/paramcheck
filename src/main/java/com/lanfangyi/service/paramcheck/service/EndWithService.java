package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Among;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.EndWith;
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
@RequestMapping("/endWith")
public class EndWithService {

    @GetMapping("/test")
    @Valid(addErrLog = true, errLogLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<Integer> test(@EndWith("1")  int a, @EndWith("a") String b ) {
        System.out.println(a);
        return BaseResponse.success(a);
    }

    @GetMapping("/test2")
    @Valid
    public BaseResponse<Integer> test2(@EndWith("1")  int a, @EndWith("a") String b ,@EndWith("") String aa) {
        System.out.println(a);
        return BaseResponse.success(a);
    }


}
