package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Check;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotNull;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.constants.ErrorCode;
import com.lanfangyi.service.paramcheck.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestService {

    @PostMapping("/test")
    @Valid(msgClass = ErrorCode.class, msgClassStaticField = "PARAM_ERROR")
    public String test( @RequestBody @Check User user) {
        return "sss";
    }

    @GetMapping("/test2")
    @Valid(msgClass = ErrorCode.class, msgClassStaticField = "PARAM_ERROR2")
    public ValidateResult test2(@NotNull @NotBlank(among = {"da","d"}) String a) {
        return new ValidateResult();
    }

    @GetMapping("/test3")
    @Valid
    public String test3() {
//        Map<String, Object> testCase = ServiceMethodTestUtil.getTestCase(null, null, 0);
//        System.out.println(testCase);
        ValidateResult validateResult = this.test2("a");
        System.out.println(validateResult);
        return "aaa";
    }

}
