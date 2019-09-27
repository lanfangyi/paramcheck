package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Valid;
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
@RequestMapping("/max")
public class MaxService {

    @GetMapping("/test")
    @Valid(addErrLog = true, errLogLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<Integer> test(@Max(10)  int a, @Max(5) Long b ) {
        System.out.println(a);
        return BaseResponse.success(a);
    }


}
