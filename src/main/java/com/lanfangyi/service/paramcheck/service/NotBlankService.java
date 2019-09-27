package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
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
@RequestMapping("/notBlank")
public class NotBlankService {

    @GetMapping("/test")
    @Valid(addErrLog = true, errLogLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<String> test(@NotBlank String a) {
        System.out.println(a);
        return BaseResponse.success(a);
    }

    @GetMapping("/test2")
    @Valid(addErrLog = true, errLogLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<String> test2(@NotBlank(startWith = "1", endWith = "1", maxLength = 20, minLength = 3, contain = "3", among = {"12341", "1234331"}) String a) {
        System.out.println(a);
        return BaseResponse.success(a);
    }
}
