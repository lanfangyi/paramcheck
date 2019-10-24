package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Check;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Among;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Range;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.constants.ErrorCode;
import com.lanfangyi.service.paramcheck.domain.User;
import com.lanfangyi.service.paramcheck.resp.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@RestController
@RequestMapping("/test")
public class TestService {

    @PostMapping("/test")
    @Valid(msgClass = ErrorCode.class, msgClassStaticField = "PARAM_ERROR")
    public String test(@RequestBody @Check User user) {
        return "sss";
    }

    @GetMapping("/test2")
//    @Valid(msgClass = ErrorCode.class, msgClassStaticField = "PARAM_ERROR2")
    public ValidateResult test2(@NotBlank(among = {"da", "d"}, minLength = 2) String a) {
        System.out.println("-----");
        return new ValidateResult();
    }

    @GetMapping("/test3")
    @Valid
    public String test3(@Among({1, 2, 3}) @Range(min = 1, max = 3) Integer a) {
//        Map<String, Object> testCase = ServiceMethodTestUtil.getTestCase(null, null, 0);
//        System.out.println(testCase);
        ValidateResult validateResult = this.test2("a");
        System.out.println(validateResult);
        return "aaa";
    }

    @PostMapping("/test4")
    @Valid()
    public BaseResponse<String> test4(@RequestBody @Check User user) {
        return BaseResponse.success("-----");
    }

}
